package com.ipeasa.models

import org.jetbrains.exposed.sql.Table

object ProductTable : Table("products") {
    val id = long("id").autoIncrement()
    val productId = uuid("product_id")
    val name = varchar("name", 45)
    val price = double("price")
    val currency = varchar("currency", 3)
    val state = bool("state").default(true)
    override val primaryKey = PrimaryKey(id, name = "pk_product")
}