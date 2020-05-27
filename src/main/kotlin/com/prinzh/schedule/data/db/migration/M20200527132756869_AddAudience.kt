package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Audiences
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527132756869_AddAudience : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Audiences)
    }

    override fun down() {
        SchemaUtils.drop(Audiences)
    }
}