package com.ipeasa.models

import com.ipeasa.models.OrderProductTable.references
import org.jetbrains.exposed.sql.Table

object OrderProductTable : Table("order_products") {
    val id = long("id")
    val productId = long("product_id").references(ProductTable.id)
    val orderId = long("order_id").references(OrderTable.id)
    val name = varchar("name", 45)
    val price = double("price")
    val currency = varchar("currency", 3)
    val quantity = double("quantity")
}