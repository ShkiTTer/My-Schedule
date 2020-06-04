package com.prinzh.schedule.app.responses.common

interface IResponseConverter<in E, out T> {
    fun fromDomain(data: E): T
}