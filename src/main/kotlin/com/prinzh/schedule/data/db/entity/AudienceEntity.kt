package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Audience
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object Audiences : UUIDTable("audience") {
    val audienceNumber = varchar("audience_number", 5)
    val building = reference("building", Buildings, ReferenceOption.CASCADE)
}

class AudienceEntity(id: EntityID<UUID>) : UUIDEntity(id), IEntityConverter<Audience> {
    companion object : UUIDEntityClass<AudienceEntity>(Audiences)

    var audienceNumber by Audiences.audienceNumber
    var building by BuildingEntity referencedOn Audiences.building

    override fun toDomain(): Audience = Audience(id.value, audienceNumber, building.toDomain())
}