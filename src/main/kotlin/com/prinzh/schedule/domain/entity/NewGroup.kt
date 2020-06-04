package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewGroup(
    val title: String,
    val facultyId: UUID,
    val parentId: UUID?
): INewEntity