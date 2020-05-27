package com.prinzh.schedule.domain.common.response

abstract class Response(val info: ResponseInfo)

class DataResponse<out T>(info: ResponseInfo, val result: T): Response(info)

class EmptyResponse(info: ResponseInfo): Response(info)