package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Building
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Buildings:UUIDTable("building") {
    val title = varchar("title", 150)
    val address = varchar("address", 200)
}

class BuildingEntity(id:EntityID<UUID>): UUIDEntity(id), IEntityConverter<Building> {
    companion object: UUIDEntityClass<BuildingEntity>(Buildings)

    var title by Buildings.title
    var address by Buildings.address

    override fun toDomain(): Building = Building(id.value, title, address)
}