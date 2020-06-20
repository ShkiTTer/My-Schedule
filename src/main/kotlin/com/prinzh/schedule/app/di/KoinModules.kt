package com.prinzh.schedule.app.di

import com.prinzh.schedule.app.services.*
import com.prinzh.schedule.app.services.interfaces.*
import com.prinzh.schedule.data.repository.*
import com.prinzh.schedule.domain.repository.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

@KtorExperimentalAPI
val serviceModule = module {
    single<IFacultyService> {
        FacultyServiceImpl(repository = get())
    }

    single<IBuildingService> {
        BuildingServiceImpl(repository = get())
    }

    single<ISubjectService> {
        SubjectServiceImpl(repository = get())
    }

    single<ITeacherService> {
        TeacherServiceImpl(repository = get())
    }

    single<IAudienceService> {
        AudienceServiceImpl(
            audienceRepository = get()
        )
    }

    single<IGroupService> {
        GroupServiceImpl(
            groupRepository = get()
        )
    }

    single<ILessonTypeService> {
        LessonTypeServiceImpl(
            repository = get()
        )
    }

    single<ITeacherDisciplineService> {
        TeacherDisciplineServiceImpl(
            teacherDisciplineRepository = get()
        )
    }

    single<IUserService> {
        UserServiceImpl(
            userRepository = get()
        )
    }

    single<IScheduleService> {
        ScheduleServiceImpl(
            scheduleRepository = get()
        )
    }

    single<IAuthService> {
        AuthServiceImpl(
            userRepository = get(),
            refreshTokenRepository = get()
        )
    }

    single<IRoleService> {
        RoleServiceImpl(
            roleRepository = get()
        )
    }
}

@KtorExperimentalAPI
val repositoryModule = module {
    singleBy<IFacultyRepository, FacultyRepositoryImpl>()
    singleBy<IBuildingRepository, BuildingRepositoryImpl>()
    singleBy<ISubjectRepository, SubjectRepositoryImpl>()
    singleBy<ITeacherRepository, TeacherRepositoryImpl>()
    singleBy<IAudienceRepository, AudienceRepositoryImpl>()
    singleBy<IGroupRepository, GroupRepositoryImpl>()
    singleBy<ILessonTypeRepository, LessonTypeRepositoryImpl>()
    singleBy<ITeacherDisciplineRepository, TeacherDisciplineRepositoryImpl>()
    singleBy<IRoleRepository, RoleRepositoryImpl>()
    singleBy<IUserRepository, UserRepositoryImpl>()
    singleBy<IScheduleRepository, ScheduleRepositoryImpl>()
    singleBy<IRefreshTokenRepository, RefreshTokenRepositoryImpl>()
}