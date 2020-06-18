package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Group
import com.prinzh.schedule.domain.entity.NewGroup
import java.util.*

interface IGroupRepository : ICrudRepository<NewGroup, Group, UUID> {
    suspend fun getByFaculty(facultyId: UUID): List<Group>
}