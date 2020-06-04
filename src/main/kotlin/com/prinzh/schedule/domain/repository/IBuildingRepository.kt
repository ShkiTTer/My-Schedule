package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.entity.NewBuilding
import java.util.*

interface IBuildingRepository: ICrudRepository<NewBuilding, Building, UUID> {
}