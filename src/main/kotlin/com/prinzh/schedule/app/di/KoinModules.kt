package com.prinzh.schedule.app.di

import com.prinzh.schedule.data.services.FacultyServiceImpl
import com.prinzh.schedule.domain.services.IFacultyService
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

@KtorExperimentalAPI
val serviceModule = module {
    singleBy<IFacultyService, FacultyServiceImpl>()
}