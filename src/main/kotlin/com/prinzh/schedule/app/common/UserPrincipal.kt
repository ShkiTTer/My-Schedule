package com.prinzh.schedule.app.common

import io.ktor.auth.Principal
import java.util.*

data class UserPrincipal(
    val id: UUID,
    val role: UUID
) : Principal