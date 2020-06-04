package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.User
import java.util.*

data class UserResponse(
    val id: UUID,
    val login: String,
    val mail: String,
    val roles: List<RoleResponse>
) : IResponseContent {
    companion object : IResponseConverter<User, UserResponse> {
        override fun fromDomain(data: User): UserResponse = UserResponse(
            data.id!!,
            data.login,
            data.mail,
            data.roles.map { RoleResponse.fromDomain(it) }
        )
    }
}