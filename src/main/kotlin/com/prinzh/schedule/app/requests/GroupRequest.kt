package com.prinzh.schedule.app.requests

data class GroupRequest(
    val title: String,
    val faculty: String,
    val parentGroup: String?
)