package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.app.responses.ShortGroupResponse
import java.util.*

interface IGroupService : ICrudService<GroupRequest, UUID> {
    suspend fun getByFaculty(facultyId: UUID): List<ShortGroupResponse>
    suspend fun getParents(groupId: UUID): List<ShortGroupResponse>
}