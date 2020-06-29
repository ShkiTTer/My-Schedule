package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Group(
    val id: UUID,
    val title: String,
    val faculty: Faculty,
    val parentGroup: Group? = null,
    val childGroups: List<Group>
): IDataEntity