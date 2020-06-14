package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewTeacher
import com.prinzh.schedule.domain.entity.Teacher
import java.util.*

interface ITeacherRepository: ICrudRepository<NewTeacher, Teacher, UUID> {
    suspend fun getBySurname(surname: String): List<Teacher>
    suspend fun getByName(name: String): List<Teacher>
    suspend fun getByPatronymic(patronymic: String): List<Teacher>
}