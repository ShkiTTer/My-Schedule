package com.prinzh.schedule.app.requests

data class ScheduleRequest(
    val group: String?,
    val teacher: String?,
    val subject: String?,
    val audience: String?,
    val lessonType: String?,
    val day: Int?,
    val lessonNumber: Int?,
    val weekStart: Int?,
    val weekEnd: Int?
)