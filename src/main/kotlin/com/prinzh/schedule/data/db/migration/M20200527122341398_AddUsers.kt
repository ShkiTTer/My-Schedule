package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Users
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527122341398_AddUsers : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Users)
    }

    override fun down() {
        SchemaUtils.drop(Users)
    }
}