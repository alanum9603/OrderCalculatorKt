package com.ipeasa.models

import org.jetbrains.exposed.sql.Table

object MaterialTable : Table("materials") {
    val id = long("id").autoIncrement()
    val materialId = uuid("material_id").uniqueIndex()
    val name = varchar("name", 45)
    val price = double("price")
    val currency = varchar("currency", 3)
    val unit = varchar("unit", 25)
    val state = bool("state").default(true)
    override val primaryKey = PrimaryKey(id, name = "pk_material")
}