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

    override suspend fun create(data: TeacherDisciplineRequest): List<TeacherDisciplineResponse> {
        val subjectIds = data.subjects?.map {
            it.toUUID()
        } ?: throw BadRequestException("Invalid credentials")

        val existDisciplines = teacherDisciplineRepository.getByTeacher(data.teacher.toUUID())

        if (existDisciplines.isNotEmpty()) {
            throw BadRequestException("Invalid credentials")
        }

        return subjectIds.map {
            teacherDisciplineRepository.create(
                NewTeacherDiscipline(
                    data.teacher.toUUID(),
                    it
                )
            )
        }.map { TeacherDisciplineResponse.fromDomain(it) }
    }

    override suspend fun update(data: TeacherDisciplineRequest): List<TeacherDisciplineResponse> {
        val subjectIds = data.subjects?.map {
            it.toUUID()
        } ?: throw BadRequestException("Invalid credentials")

        val existDisciplines = teacherDisciplineRepository.getByTeacher(data.teacher.toUUID())

        if (existDisciplines.isEmpty()) {
            throw BadRequestException("Invalid credentials")
        }

        val toDelete =
            (existDisciplines.map { it.subject.id } - subjectIds).map { s ->
                existDisciplines.find {
                    it.subject.id == s
                }?.id ?: throw BadRequestException("Invalid credentials")
            }
        val toAdd = subjectIds - existDisciplines.map { it.subject.id }

        toAdd.forEach {
            teacherDisciplineRepository.create(
                NewTeacherDiscipline(data.teacher.toUUID(), it)
            )
        }

        toDelete.forEach {
            teacherDisciplineRepository.delete(it)
        }

        return teacherDisciplineRepository.getByTeacher(data.teacher.toUUID()).map {
            TeacherDisciplineResponse.fromDomain(it)
        }
    }

    override suspend fun delete(teacherId: UUID) {
        val ids = teacherDisciplineRepository.getByTeacher(teacherId).map { it.id }

        ids.forEach {
            teacherDisciplineRepository.delete(it)
        }
    }
}