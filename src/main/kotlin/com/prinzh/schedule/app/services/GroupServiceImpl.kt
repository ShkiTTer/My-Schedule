package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.app.responses.FullGroupResponse
import com.prinzh.schedule.app.responses.ShortGroupResponse
import com.prinzh.schedule.app.services.interfaces.IGroupService
import com.prinzh.schedule.domain.entity.NewGroup
import com.prinzh.schedule.domain.repository.IGroupRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class GroupServiceImpl(
    private val groupRepository: IGroupRepository
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

        return groupRepository.create(
            NewGroup(
                title = data.title,
                facultyId = data.faculty.toUUID(),
                parentId = data.parentGroup?.toUUID()
            )
        ).let {
            FullGroupResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: GroupRequest): FullGroupResponse {
        if (data.title.isNullOrEmpty() || data.faculty.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return groupRepository.update(
            id,
            NewGroup(
                title = data.title,
                facultyId = data.faculty.toUUID(),
                parentId = data.parentGroup?.toUUID()
            )
        ).let {
            FullGroupResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        groupRepository.delete(id)
    }

    override suspend fun getByFaculty(facultyId: UUID): List<ShortGroupResponse> {
        return groupRepository.getByFaculty(facultyId).map {
            ShortGroupResponse.fromDomain(it)
        }
    }
}