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
    companion object {
        private const val MAX_DAY = 6
        private const val MIN_DAY = 1
        private const val MAX_LESSON_NUMBER = 7
        private const val MIN_LESSON_NUMBER = 1
    }

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

        if (data.weekEnd < data.weekStart)
            throw BadRequestException("Invalid credentials")

        if (data.day < MIN_DAY || data.day > MAX_DAY)
            throw BadRequestException("Invalid credentials")

        if (data.lessonNumber < MIN_LESSON_NUMBER || data.lessonNumber > MAX_LESSON_NUMBER)
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

        if (data.weekEnd < data.weekStart)
            throw BadRequestException("Invalid credentials")

        if (data.day < MIN_DAY || data.day > MAX_DAY)
            throw BadRequestException("Invalid credentials")

        if (data.lessonNumber < MIN_LESSON_NUMBER || data.lessonNumber > MAX_LESSON_NUMBER)
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

    override suspend fun getByGroup(groupId: UUID, week: Int): List<IResponseContent> {
        return scheduleRepository.getByGroup(groupId, week).map {
            ScheduleResponse.fromDomain(it)
        }
    }
}