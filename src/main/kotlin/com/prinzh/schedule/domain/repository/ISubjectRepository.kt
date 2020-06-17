package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewSubject
import com.prinzh.schedule.domain.entity.Subject
import java.util.*

interface ISubjectRepository: ICrudRepository<NewSubject, Subject, UUID> {
    suspend fun getByTeacher(teacherId: UUID): List<Subject>
}