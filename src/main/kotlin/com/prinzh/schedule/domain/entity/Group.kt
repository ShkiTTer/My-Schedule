package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Group(
    val id: UUID,
    val title: String,
    val faculty: Faculty,
    val parentGroup: Group? = null,
    val childGroups: List<Group>
): IDataEntity {
    override fun equals(other: Any?): Boolean {
        return this.id == (other as Group).id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + faculty.hashCode()
        result = 31 * result + (parentGroup?.hashCode() ?: 0)
        result = 31 * result + childGroups.hashCode()
        return result
    }
}