package com.ipeasa.models

import org.jetbrains.exposed.sql.Table

object OrderProductMaterialTable : Table("order_product_materials") {
    val id = long("id")
    val materialId = long("material_id").references(MaterialTable.id)
    val orderProductId = long("order_product_id").references(OrderProductTable.id)
    val name = varchar("name", 45)
    val price = double("price")
    val currency = varchar("currency", 3)
    val unit = varchar("unit", 25)
    val quantity = double("quantity")
}