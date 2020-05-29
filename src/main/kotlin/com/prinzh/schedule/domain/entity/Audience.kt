package com.prinzh.schedule.domain.entity

import java.util.*

data class Audience(
    val id: UUID? = null,
    val audienceNumber: String,
    val building: Building
)