package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.responses.common.IResponseContent

interface ICrudService<in IN, in ID> {
    suspend fun getAll(): List<IResponseContent>
    suspend fun getById(id: ID): IResponseContent
    suspend fun create(data: IN): IResponseContent
    suspend fun update(id: ID, data: IN): IResponseContent
    suspend fun delete(id: ID)
}