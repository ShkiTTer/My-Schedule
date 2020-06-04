package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.FacultyEntity
import com.prinzh.schedule.data.db.entity.GroupEntity
import com.prinzh.schedule.domain.entity.Group
import com.prinzh.schedule.domain.entity.NewGroup
import com.prinzh.schedule.domain.repository.IGroupRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class GroupRepositoryImpl: IGroupRepository {
    override suspend fun getAll(): List<Group> = dbQuery {
        GroupEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Group? = dbQuery {
        GroupEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewGroup): Group = dbQuery {
        val faculty = FacultyEntity.findById(entity.facultyId) ?: throw NotFoundException()

        val parent = if (entity.parentId != null)
            GroupEntity.findById(entity.parentId)
        else null

        GroupEntity.new {
            this.title = entity.title
            this.faculty = faculty
            this.parentGroup = parent
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewGroup): Group = dbQuery {
        val group = GroupEntity.findById(id) ?: throw NotFoundException()

        val faculty = FacultyEntity.findById(entity.facultyId) ?: throw NotFoundException()

        val parent = if (entity.parentId != null)
            GroupEntity.findById(entity.parentId)
        else null

        group.apply {
            this.title = entity.title
            this.faculty = faculty
            this.parentGroup = parent
        }.toDomain()
    }

    override suspend fun delete(id: UUID) {
        val group = GroupEntity.findById(id) ?: throw NotFoundException()
        group.delete()
    }
}