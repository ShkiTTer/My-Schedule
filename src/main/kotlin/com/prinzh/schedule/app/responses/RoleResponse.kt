package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.domain.entity.Role
import java.util.*

data class RoleResponse(
    val id: UUID,
    val role: String
) : IResponseContent {
    companion object : IResponseConverter<Role, RoleResponse> {
        override fun fromDomain(data: Role): RoleResponse = RoleResponse(
            data.id!!,
            data.role.toString()
        )
    }
}