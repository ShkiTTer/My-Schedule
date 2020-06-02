package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.User
import java.util.*

interface IUserRepository: ICrudRepository<User, UUID> {
}