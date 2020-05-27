package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Schedules
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527143317092_AddSchedule : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Schedules)
    }

    override fun down() {
        SchemaUtils.drop(Schedules)
    }
}