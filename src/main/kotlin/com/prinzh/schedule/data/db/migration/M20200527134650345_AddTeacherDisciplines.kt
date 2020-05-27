package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.LessonTypes
import com.prinzh.schedule.data.db.entity.Subjects
import com.prinzh.schedule.data.db.entity.TeacherDisciplines
import com.prinzh.schedule.data.db.entity.Teachers
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527134650345_AddTeacherDisciplines : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Teachers, Subjects, LessonTypes, TeacherDisciplines)
    }

    override fun down() {
        SchemaUtils.drop(Teachers, Subjects, LessonTypes, TeacherDisciplines)
    }
}