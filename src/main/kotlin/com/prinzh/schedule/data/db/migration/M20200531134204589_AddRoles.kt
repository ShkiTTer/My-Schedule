package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Roles
import com.prinzh.schedule.data.db.entity.UserRoles
import com.prinzh.schedule.data.db.entity.Users
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200531134204589_AddRoles : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Roles, UserRoles)
    }

    override fun down() {
        SchemaUtils.drop(UserRoles, Roles)
    }
}