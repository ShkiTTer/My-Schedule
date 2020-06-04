package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.app.responses.FullGroupResponse
import com.prinzh.schedule.app.services.interfaces.IGroupService
import com.prinzh.schedule.domain.entity.Group
import com.prinzh.schedule.domain.repository.IFacultyRepository
import com.prinzh.schedule.domain.repository.IGroupRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.lang.Exception
import java.util.*

@KtorExperimentalAPI
class GroupServiceImpl(
    private val groupRepository: IGroupRepository,
    private val facultyRepository: IFacultyRepository
) : IGroupService {
    override suspend fun getAll(): List<FullGroupResponse> {
        return groupRepository.getAll().map {
            FullGroupResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): FullGroupResponse {
        return groupRepository.getById(id)?.let {
            FullGroupResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: GroupRequest): FullGroupResponse {
        if (data.title.isNullOrEmpty() || data.faculty.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val facultyId = try {
            UUID.fromString(data.faculty)
        }
        catch (e: Exception) {
            throw BadRequestException("Invalid credentials")
        }

        val parentId = if (!data.parentGroup.isNullOrEmpty()) {
            try {
                UUID.fromString(data.parentGroup)
            }
            catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            }
        }
        else null

        val faculty = facultyRepository.getById(facultyId) ?: throw NotFoundException()
        val parent = if (parentId != null) {
            groupRepository.getById(parentId) ?: throw NotFoundException()
        }
        else null

        return groupRepository.create(
            Group(
            title = data.title,
                faculty = faculty,
                parentGroup = parent
        )).let {
            FullGroupResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: GroupRequest): FullGroupResponse {
        if (data.title.isNullOrEmpty() || data.faculty.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val facultyId = try {
            UUID.fromString(data.faculty)
        }
        catch (e: Exception) {
            throw BadRequestException("Invalid credentials")
        }

        val parentId = if (!data.parentGroup.isNullOrEmpty()) {
            try {
                UUID.fromString(data.parentGroup)
            }
            catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            }
        }
        else null

        val faculty = facultyRepository.getById(facultyId) ?: throw NotFoundException()
        val parent = if (parentId != null) {
            groupRepository.getById(parentId) ?: throw NotFoundException()
        }
        else null

        return groupRepository.update(id,
            Group(
                title = data.title,
                faculty = faculty,
                parentGroup = parent
            )).let {
            FullGroupResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        groupRepository.delete(id)
    }

}