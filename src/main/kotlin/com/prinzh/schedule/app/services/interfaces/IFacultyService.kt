package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.FacultyRequest
import com.prinzh.schedule.domain.entity.Faculty
import java.util.*

interface IFacultyService : ICrudService<FacultyRequest, Faculty, UUID> {
}