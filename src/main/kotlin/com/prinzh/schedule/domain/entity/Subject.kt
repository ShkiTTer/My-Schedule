package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Subject(
    val id: UUID? = null,
    val title: String
) : IDataEntity