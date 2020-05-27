package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Groups
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527131611550_AddGroups : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Groups)
    }

    override fun down() {
        SchemaUtils.drop(Groups)
    }
}