package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity

data class NewBuilding(
    val title: String,
    val address: String
): INewEntity