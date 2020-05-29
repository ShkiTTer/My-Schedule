package com.prinzh.schedule.app.responses.common

abstract class Response(val info: ResponseInfo)

class DataResponse<out T>(info: ResponseInfo, val result: T): Response(info)

class EmptyResponse(info: ResponseInfo): Response(info)