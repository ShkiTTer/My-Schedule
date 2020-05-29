package com.prinzh.schedule.domain.entity

import java.util.*

data class Audience(
    val id: UUID?,
    val audienceNumber: String,
    val building: Building
)