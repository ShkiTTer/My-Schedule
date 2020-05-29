package com.prinzh.schedule.app.services.interfaces

interface ICrudService<in IN, out OUT, in ID> {
    suspend fun getAll(): List<OUT>
    suspend fun getById(id: ID): OUT
    suspend fun create(data: IN): OUT
    suspend fun update(id: ID, data: IN): OUT
    suspend fun delete(id: ID)
}