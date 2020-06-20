package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewUser
import com.prinzh.schedule.domain.entity.User
import java.util.*

interface IUserRepository : ICrudRepository<NewUser, User, UUID> {
    suspend fun getByLogin(login: String): User?
    suspend fun getByMail(mail: String): User?
}