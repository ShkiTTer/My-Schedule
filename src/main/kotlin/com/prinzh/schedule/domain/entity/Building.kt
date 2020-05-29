package com.prinzh.schedule.domain.entity

import java.util.*

data class Building(
    val id: UUID? = null,
    val title: String,
    val address: String
)