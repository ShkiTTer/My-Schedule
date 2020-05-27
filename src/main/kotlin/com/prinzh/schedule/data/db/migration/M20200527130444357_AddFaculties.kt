package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Faculties
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527130444357_AddFaculties : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Faculties)
    }

    override fun down() {
        SchemaUtils.drop(Faculties)
    }
}