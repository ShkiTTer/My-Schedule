package com.prinzh.schedule.domain.services

interface ICrudService<T, ID> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: ID): T?
    suspend fun create(entity: T): T
    suspend fun update(id: ID, entity: T): T
    suspend fun delete(id: ID)
}