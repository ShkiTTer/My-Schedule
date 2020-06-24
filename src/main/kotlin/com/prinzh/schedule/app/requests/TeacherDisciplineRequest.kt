package com.prinzh.schedule.app.requests

data class TeacherDisciplineRequest(
    val teacher: String?,
    val subjects: List<String>?
)