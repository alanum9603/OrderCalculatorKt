package com.ipeasa.models

import com.ipeasa.models.MaterialTable.default
import com.ipeasa.models.MaterialTable.uniqueIndex
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object OrderTable : Table("orders") {
    val id = long("id").autoIncrement()
    val orderId = uuid("order_id").uniqueIndex()
    val ruc = varchar("ruc", 20)
    val date = date("date")
    val total = double("total")
    val currency = varchar("currency", 3)
    val exchange = double("exchange_pen_to_usd")
    val address  = varchar("address", 255)
    val state = bool("state").default(true)
    override val primaryKey = PrimaryKey(id, name = "order_pk_id")
}
