package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.AudienceRequest
import com.prinzh.schedule.domain.entity.Audience
import java.util.*

interface IAudienceService: ICrudService<AudienceRequest,Audience, UUID> {
}