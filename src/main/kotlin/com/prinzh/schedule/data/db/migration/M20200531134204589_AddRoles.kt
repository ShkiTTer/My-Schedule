package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Roles
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200531134204589_AddRoles : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Roles)
    }

    override fun down() {
        SchemaUtils.drop(Roles)
    }
}