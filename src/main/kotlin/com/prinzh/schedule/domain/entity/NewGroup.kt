package com.prinzh.schedule.domain.entity

import java.util.*

data class NewGroup(
    val title: String,
    val facultyId: UUID,
    val parentId: UUID?
)