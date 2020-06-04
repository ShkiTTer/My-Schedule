package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.TeacherDisciplineRequest
import com.prinzh.schedule.app.responses.TeacherDisciplineResponse
import com.prinzh.schedule.app.services.interfaces.ITeacherDisciplineService
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
    private val teacherDisciplineRepository: ITeacherDisciplineRepository,
    private val teacherRepository: ITeacherRepository,
    private val subjectRepository: ISubjectRepository,
    private val lessonTypeRepository: ILessonTypeRepository
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
        val teacherId = parseId(data.teacher)
        val subjectId = parseId(data.subject)
        val lessonTypeId = parseId(data.lessonType)

        val teacher = teacherRepository.getById(teacherId) ?: throw NotFoundException()
        val subject = subjectRepository.getById(subjectId) ?: throw NotFoundException()
        val lessonTye = lessonTypeRepository.getById(lessonTypeId) ?: throw NotFoundException()

        return teacherDisciplineRepository.create(
            TeacherDiscipline(
                teacher = teacher,
                subject = subject,
                lessonType = lessonTye
            )
        ).let {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: TeacherDisciplineRequest): TeacherDisciplineResponse {
        val teacherId = parseId(data.teacher)
        val subjectId = parseId(data.subject)
        val lessonTypeId = parseId(data.lessonType)

        val teacher = teacherRepository.getById(teacherId) ?: throw NotFoundException()
        val subject = subjectRepository.getById(subjectId) ?: throw NotFoundException()
        val lessonTye = lessonTypeRepository.getById(lessonTypeId) ?: throw NotFoundException()

        return teacherDisciplineRepository.update(id,
            TeacherDiscipline(
                teacher = teacher,
                subject = subject,
                lessonType = lessonTye
            )
        ).let {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        teacherDisciplineRepository.delete(id)
    }

    private fun parseId(id: String): UUID {
        return try {
            UUID.fromString(id)
        }
        catch (e: Exception) {
            throw BadRequestException("Invalid credentials")
        }
    }
}