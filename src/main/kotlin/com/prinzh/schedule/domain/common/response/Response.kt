package com.prinzh.schedule.domain.common.response

abstract class Response(val info: ResponseInfo)

class DataResponse<out T>(info: ResponseInfo, result: T): Response(info)

class ErrorResponse(info: ResponseInfo): Response(info)