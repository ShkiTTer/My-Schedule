package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.UserRequest
import com.prinzh.schedule.app.responses.UserResponse
import com.prinzh.schedule.app.services.interfaces.IUserService
import com.prinzh.schedule.domain.entity.Role
import com.prinzh.schedule.domain.entity.User
import com.prinzh.schedule.domain.repository.IRoleRepository
import com.prinzh.schedule.domain.repository.IUserRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class UserServiceImpl(private val userRepository: IUserRepository, private val roleRepository: IRoleRepository) :
    IUserService {
    override suspend fun getAll(): List<UserResponse> {
        return userRepository.getAll().map {
            UserResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): UserResponse {
        return userRepository.getById(id)?.let {
            UserResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: UserRequest): UserResponse {
        if (data.login.isNullOrEmpty() || data.password.isNullOrEmpty()
            || data.mail.isNullOrEmpty() || data.roles.isNullOrEmpty()
        ) {
            throw BadRequestException("Invalid credentials")
        }

        val roles = mutableListOf<Role>()
        data.roles.forEach {
            val roleId = try {
                UUID.fromString(it)
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            }

            roles.add(roleRepository.getById(roleId) ?: throw NotFoundException())
        }

        return userRepository.create(
            User(
                login = data.login,
                password = data.password,
                mail = data.mail,
                roles = roles
            )
        ).let {
            UserResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: UserRequest): UserResponse {
        if (data.login.isNullOrEmpty() || data.password.isNullOrEmpty()
            || data.mail.isNullOrEmpty() || data.roles.isNullOrEmpty()
        ) {
            throw BadRequestException("Invalid credentials")
        }

        val roles = mutableListOf<Role>()
        data.roles.forEach {
            val roleId = try {
                UUID.fromString(it)
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            }

            roles.add(roleRepository.getById(roleId) ?: throw NotFoundException())
        }

        return userRepository.update(id,
            User(
                login = data.login,
                password = data.password,
                mail = data.mail,
                roles = roles
            )
        ).let {
            UserResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        userRepository.delete(id)
    }
}