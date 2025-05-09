package com.ipeasa.models

import org.jetbrains.exposed.sql.Table

object ProductDetailTable : Table("products_detail") {
    val id = long("id").autoIncrement()
    val productId = long("product_id").references(ProductTable.id)
    val materialId = long("material_id").references(MaterialTable.id)
    val quantity = double("quantity")
    override val primaryKey = PrimaryKey(id, name = "pk_product_detail")
}