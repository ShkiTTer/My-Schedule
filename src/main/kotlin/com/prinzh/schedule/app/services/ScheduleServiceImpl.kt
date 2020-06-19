package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.ScheduleRequest
import com.prinzh.schedule.app.responses.ScheduleResponse
import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.services.interfaces.IScheduleService
import com.prinzh.schedule.domain.entity.NewSchedule
import com.prinzh.schedule.domain.repository.IScheduleRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class ScheduleServiceImpl(private val scheduleRepository: IScheduleRepository) : IScheduleService {
    override suspend fun getAll(): List<IResponseContent> {
        return scheduleRepository.getAll().map { ScheduleResponse.fromDomain(it) }
    }

    override suspend fun getById(id: UUID): IResponseContent {
        return scheduleRepository.getById(id)?.let {
            ScheduleResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: ScheduleRequest): IResponseContent {
        if (data.day == null || data.lessonNumber == null || data.weekStart == null || data.weekEnd == null)
            throw BadRequestException("Invalid credentials")

        return scheduleRepository.create(
            NewSchedule(
                groupId = data.group.toUUID(),
                teacherId = data.teacher.toUUID(),
                subjectId = data.subject.toUUID(),
                typeId = data.lessonType.toUUID(),
                audienceId = data.audience.toUUID(),
                lessonNumber = data.lessonNumber,
                day = data.day,
                weekStart = data.weekStart,
                weekEnd = data.weekEnd
            )
        ).let {
            ScheduleResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: ScheduleRequest): IResponseContent {
        if (data.day == null || data.lessonNumber == null || data.weekStart == null || data.weekEnd == null)
            throw BadRequestException("Invalid credentials")

        return scheduleRepository.update(
            id,
            NewSchedule(
                groupId = data.group.toUUID(),
                teacherId = data.teacher.toUUID(),
                subjectId = data.subject.toUUID(),
                typeId = data.lessonType.toUUID(),
                audienceId = data.audience.toUUID(),
                lessonNumber = data.lessonNumber,
                day = data.day,
                weekStart = data.weekStart,
                weekEnd = data.weekEnd
            )
        ).let {
            ScheduleResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        scheduleRepository.delete(id)
    }

    override suspend fun getByTeacher(teacherId: UUID, week: Int): List<IResponseContent> {
        return scheduleRepository.getByTeacher(teacherId, week).map {
            ScheduleResponse.fromDomain(it)
        }
    }
}