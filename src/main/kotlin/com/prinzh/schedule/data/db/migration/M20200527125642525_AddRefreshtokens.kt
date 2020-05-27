package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.RefreshTokens
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527125642525_AddRefreshtokens : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(RefreshTokens)
    }

    override fun down() {
        SchemaUtils.drop(RefreshTokens)
    }
}