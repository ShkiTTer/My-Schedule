package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Subject
import java.util.*

interface ISubjectRepository: ICrudRepository<Subject, UUID> {
}