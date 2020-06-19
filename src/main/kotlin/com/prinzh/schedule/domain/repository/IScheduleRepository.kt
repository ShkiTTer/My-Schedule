package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewSchedule
import com.prinzh.schedule.domain.entity.Schedule
import java.util.*

interface IScheduleRepository : ICrudRepository<NewSchedule, Schedule, UUID> {
    suspend fun getByTeacher(teacherId: UUID, week: Int): List<Schedule>
}