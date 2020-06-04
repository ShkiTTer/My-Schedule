package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.domain.entity.Group
import java.util.*

interface IGroupService: ICrudService<GroupRequest, UUID> {
}