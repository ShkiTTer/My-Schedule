package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Audience
import com.prinzh.schedule.domain.entity.NewAudience
import java.util.*

interface IAudienceRepository: ICrudRepository<NewAudience, Audience, UUID> {
}