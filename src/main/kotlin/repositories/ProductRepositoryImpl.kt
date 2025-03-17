package com.ipeasa.repositories

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.ddds.ProductDetail
import com.ipeasa.models.ProductDetailTable
import com.ipeasa.models.ProductTable
import com.ipeasa.models.ProductTable.autoIncrement
import com.ipeasa.models.ProductTable.bool
import com.ipeasa.models.ProductTable.default
import com.ipeasa.models.ProductTable.double
import com.ipeasa.models.ProductTable.long
import com.ipeasa.models.ProductTable.uuid
import com.ipeasa.models.ProductTable.varchar
import com.ipeasa.utils.UuidService
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepositoryImpl(
    private val uuidService: UuidService
) : ProductRepository {
    private fun rowToProduct(row: ResultRow) : Product {
        return Product(
            id = row[ProductTable.productId].toString(),
            name = row[ProductTable.name],
            price = row[ProductTable.price],
            currency = row[ProductTable.currency]
        )
    }

    private fun productTableToProductAndDetail(rows: List<ResultRow>) : ProductAndDetail {
        return ProductAndDetail(
            id = rows.first()[ProductTable.productId].toString(),
            name = rows.first()[ProductTable.name],
            price = rows.first()[ProductTable.price],
            currency = rows.first()[ProductTable.currency],
            materials = rows.map { ProductDetail(
                it[ProductDetailTable.materialId].toString(),
                it[ProductDetailTable.quantity]
            ) }
        )
    }

    override fun getAllProducts(pageSize: Int, page: Long): List<Product> {
        return transaction {
            ProductTable
                .selectAll()
                .where { ProductTable.state eq true }
                .orderBy(ProductTable.id to SortOrder.ASC)
                .limit(pageSize).offset((page - 1) * pageSize)
                .map { rowToProduct(it) }
        }
    }

    override fun getProductsByName(name: String, pageSize: Int, page: Long): List<Product> {
        return transaction {
            ProductTable
                .selectAll()
                .where { ProductTable.name like "%$name%" }
                .andWhere { ProductTable.state eq true }
                .orderBy(ProductTable.id to SortOrder.ASC)
                .limit(pageSize).offset((page - 1) * pageSize)
                .map { rowToProduct(it) }
        }
    }

    override fun getProductByUuid(id: String): ProductAndDetail? {
        val uuid = uuidService.toValidUuid(id)

        return transaction {
            try {
                (ProductTable leftJoin ProductDetailTable)
                    .selectAll()
                    .where { ProductTable.productId eq uuid }
                    .orderBy(ProductTable.id to SortOrder.ASC)
                    .groupBy { it[ProductTable.id] }
                    .let { productTableToProductAndDetail(it.values.flatten()) }
            } catch (ex: Exception) {
                null
            }
        }

    }

    override fun postProduct(material: Product): Product? {
        TODO("Not yet implemented")
    }

    override fun putProduct(material: Product): Product? {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: String): Product? {
        TODO("Not yet implemented")
    }
}