package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.TeacherDisciplineRequest
import com.prinzh.schedule.app.responses.TeacherDisciplineResponse
import com.prinzh.schedule.app.services.interfaces.ITeacherDisciplineService
import com.prinzh.schedule.domain.entity.NewTeacherDiscipline
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import com.prinzh.schedule.domain.repository.ILessonTypeRepository
import com.prinzh.schedule.domain.repository.ISubjectRepository
import com.prinzh.schedule.domain.repository.ITeacherDisciplineRepository
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherDisciplineServiceImpl(
    private val teacherDisciplineRepository: ITeacherDisciplineRepository
) : ITeacherDisciplineService {
    override suspend fun getAll(): List<TeacherDisciplineResponse> {
        return teacherDisciplineRepository.getAll().map {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): TeacherDisciplineResponse {
        return teacherDisciplineRepository.getById(id)?.let {
            TeacherDisciplineResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: TeacherDisciplineRequest): TeacherDisciplineResponse {
        return teacherDisciplineRepository.create(
            NewTeacherDiscipline(
                teacherId = data.teacher.toUUID(),
                subjectId = data.subject.toUUID(),
                typeId = data.lessonType.toUUID()
            )
        ).let {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: TeacherDisciplineRequest): TeacherDisciplineResponse {
        return teacherDisciplineRepository.update(
            id,
            NewTeacherDiscipline(
                teacherId = data.teacher.toUUID(),
                subjectId = data.subject.toUUID(),
                typeId = data.lessonType.toUUID()
            )
        ).let {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        teacherDisciplineRepository.delete(id)
    }
}