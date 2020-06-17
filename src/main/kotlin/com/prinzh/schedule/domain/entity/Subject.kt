package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*
import javax.management.monitor.StringMonitor

data class Subject(
    val id: UUID,
    val title: String,
    val teachers: List<Teacher>
) : IDataEntity