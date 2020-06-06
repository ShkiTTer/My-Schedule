package com.prinzh.schedule.app.common

import io.ktor.auth.Principal
import java.util.*

data class UserCredentials(
    val id: UUID,
    val roles: List<UUID>
) : Principal