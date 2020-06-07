package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.LessonTypeEntity
import com.prinzh.schedule.domain.entity.LessonType
import com.prinzh.schedule.domain.entity.NewLessonType
import com.prinzh.schedule.domain.repository.ILessonTypeRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class LessonTypeRepositoryImpl : ILessonTypeRepository {
    override suspend fun getAll(): List<LessonType> = dbQuery {
        LessonTypeEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): LessonType? = dbQuery {
        LessonTypeEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewLessonType): LessonType = dbQuery {
        LessonTypeEntity.new {
            type = entity.type
            color = entity.color
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewLessonType): LessonType = dbQuery {
        val lesson = LessonTypeEntity.findById(id) ?: throw NotFoundException()

        lesson.apply {
            type = entity.type
            color = entity.color
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val lesson = LessonTypeEntity.findById(id) ?: throw NotFoundException()
        lesson.delete()
    }
}