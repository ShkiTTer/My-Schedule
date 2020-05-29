package com.prinzh.schedule.app.di

import com.prinzh.schedule.app.services.BuildingServiceImpl
import com.prinzh.schedule.app.services.FacultyServiceImpl
import com.prinzh.schedule.app.services.SubjectServiceImpl
import com.prinzh.schedule.app.services.TeacherServiceImpl
import com.prinzh.schedule.app.services.interfaces.IBuildingService
import com.prinzh.schedule.app.services.interfaces.IFacultyService
import com.prinzh.schedule.app.services.interfaces.ISubjectService
import com.prinzh.schedule.app.services.interfaces.ITeacherService
import com.prinzh.schedule.data.repository.BuildingRepositoryImpl
import com.prinzh.schedule.data.repository.FacultyRepositoryImpl
import com.prinzh.schedule.data.repository.SubjectRepositoryImpl
import com.prinzh.schedule.data.repository.TeacherRepositoryImpl
import com.prinzh.schedule.domain.repository.IBuildingRepository
import com.prinzh.schedule.domain.repository.IFacultyRepository
import com.prinzh.schedule.domain.repository.ISubjectRepository
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

@KtorExperimentalAPI
val serviceModule = module {
    single<IFacultyService> { FacultyServiceImpl(get())}
    single<IBuildingService> { BuildingServiceImpl(get())}
    single<ISubjectService> { SubjectServiceImpl(get())}
    single<ITeacherService> { TeacherServiceImpl(get())}
}

@KtorExperimentalAPI
val repositoryModule = module {
    singleBy<IFacultyRepository, FacultyRepositoryImpl>()
    singleBy<IBuildingRepository, BuildingRepositoryImpl>()
    singleBy<ISubjectRepository, SubjectRepositoryImpl>()
    singleBy<ITeacherRepository, TeacherRepositoryImpl>()
}