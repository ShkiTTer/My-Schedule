package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.domain.entity.NewFaculty
import java.util.*

interface IFacultyRepository : ICrudRepository<NewFaculty, Faculty, UUID> {
}