package com.prinzh.schedule.domain.entity

import java.util.*

data class Teacher(
    val id: UUID?,
    val surname: String,
    val name: String,
    val patronymic: String
)