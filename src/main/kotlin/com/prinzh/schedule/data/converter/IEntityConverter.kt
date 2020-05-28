package com.prinzh.schedule.data.converter

interface IEntityConverter<T> {
    fun toDomain(): T
}