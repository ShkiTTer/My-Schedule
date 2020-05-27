package com.prinzh.schedule.data.converter

interface EntityConverter<T> {
    fun toDomain(): T
}