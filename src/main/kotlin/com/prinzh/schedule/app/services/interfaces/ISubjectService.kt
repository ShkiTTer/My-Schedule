package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.SubjectRequest
import com.prinzh.schedule.app.services.interfaces.ICrudService
import com.prinzh.schedule.domain.entity.Subject
import java.util.*

interface ISubjectService: ICrudService<SubjectRequest, UUID> {
}