package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.BuildingRequest
import com.prinzh.schedule.domain.entity.Building
import java.util.*

interface IBuildingService : ICrudService<BuildingRequest, Building, UUID> {
}