package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity

data class NewTeacher(
    val surname: String,
    val name: String,
    val patronymic: String
): INewEntity