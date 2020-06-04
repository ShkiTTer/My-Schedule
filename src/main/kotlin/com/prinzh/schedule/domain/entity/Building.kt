package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Building(
    val id: UUID,
    val title: String,
    val address: String
): IDataEntity