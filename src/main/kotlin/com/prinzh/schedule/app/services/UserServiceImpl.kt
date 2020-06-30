package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.isMail
import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.common.util.HashUtil
import com.prinzh.schedule.app.requests.UserRequest
import com.prinzh.schedule.app.responses.UserResponse
import com.prinzh.schedule.app.services.interfaces.IUserService
import com.prinzh.schedule.domain.entity.NewUser
import com.prinzh.schedule.domain.entity.User
import com.prinzh.schedule.domain.repository.IUserRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class UserServiceImpl(
    private val userRepository: IUserRepository
) :
    IUserService {

    companion object {
        private const val MIN_PASSORD_LENGTH = 8
    }

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
            || data.mail.isNullOrEmpty() || data.role.isNullOrEmpty()
        ) {
            throw BadRequestException("Invalid credentials")
        }

        if (!data.mail.isMail()) throw BadRequestException("Invalid E-mail")
        if (data.password.length < MIN_PASSORD_LENGTH)
            throw BadRequestException("Password must be longer than 8 characters")

        val userByLogin = userRepository.getByLogin(data.login)
        val userByMail = userRepository.getByMail(data.mail)

        if (userByLogin != null) throw BadRequestException("User with that login already exists")
        if (userByMail != null) throw BadRequestException("User with that E-mail already exists")

        val salt = HashUtil.generateSalt()

        return userRepository.create(
            NewUser(
                login = data.login,
                password = HashUtil.hash(data.password, salt),
                mail = data.mail,
                salt = salt,
                role = data.role.toUUID()
            )
        ).let {
            UserResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: UserRequest): UserResponse {
        if (data.login.isNullOrEmpty() || data.password.isNullOrEmpty()
            || data.mail.isNullOrEmpty() || data.role.isNullOrEmpty()
        ) {
            throw BadRequestException("Invalid credentials")
        }

        if (!data.mail.isMail()) throw BadRequestException("Invalid E-mail")
        if (data.password.length < MIN_PASSORD_LENGTH)
            throw BadRequestException("Password must be longer than 8 characters")

        val userByLogin = userRepository.getByLogin(data.login)
        val userByMail = userRepository.getByMail(data.mail)

        if (userByLogin != null && userByLogin.login != data.login)
            throw BadRequestException("User with that login already exists")

        if (userByMail != null && userByMail.mail != data.mail)
            throw BadRequestException("User with that E-mail already exists")

        val salt = HashUtil.generateSalt()

        return userRepository.update(
            id,
            NewUser(
                login = data.login,
                password = HashUtil.hash(data.password, salt),
                mail = data.mail,
                salt = salt,
                role = data.role.toUUID()
            )
        ).let {
            UserResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        userRepository.delete(id)
    }

    override suspend fun checkUser(userId: UUID, roleId: UUID): User? {
        val user = userRepository.getById(userId) ?: return null

        return if (user.role.id == roleId) user else null
    }
}