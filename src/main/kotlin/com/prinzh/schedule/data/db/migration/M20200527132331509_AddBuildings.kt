package com.prinzh.schedule.data.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.prinzh.schedule.data.db.entity.Buildings
import org.jetbrains.exposed.sql.SchemaUtils

/**
 * Migration
 */
class M20200527132331509_AddBuildings : AbstractMigration() {
    override fun up() {
        SchemaUtils.create(Buildings)
    }

    override fun down() {
        SchemaUtils.drop(Buildings)
    }
}