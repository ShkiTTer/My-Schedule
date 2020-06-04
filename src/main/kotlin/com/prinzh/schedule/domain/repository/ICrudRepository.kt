package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.common.IDataEntity
import com.prinzh.schedule.domain.entity.common.INewEntity

interface ICrudRepository<NEW: INewEntity, DATA: IDataEntity, ID> {
    suspend fun getAll(): List<DATA>
    suspend fun getById(id: ID): DATA?
    suspend fun create(entity: NEW): DATA
    suspend fun update(id: ID, entity: NEW): DATA
    suspend fun delete(id: ID)
}