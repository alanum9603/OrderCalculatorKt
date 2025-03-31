package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.ddds.ProductDetail
import com.ipeasa.models.MaterialTable
import com.ipeasa.models.ProductDetailTable
import com.ipeasa.models.ProductTable
import com.ipeasa.utils.UuidService
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class ProductRepositoryImpl(
    private val uuidService: UuidService,
    private val materialRepository: MaterialRepository
) : ProductRepository {
    override fun rowToProduct(row: ResultRow): Product {
        return Product(
            id          = row[ProductTable.productId].toString(),
            name        = row[ProductTable.name],
            price       = row[ProductTable.price],
            currency    = row[ProductTable.currency]
        )
    }

    override fun rowToProductDetail(row: ResultRow) : ProductDetail {
        return ProductDetail(
            quantity = row[ProductDetailTable.quantity],
            material = Material(
                id          = row[MaterialTable.materialId].toString(),
                name        = row[MaterialTable.name],
                price       = row[MaterialTable.price],
                currency    = row[MaterialTable.currency],
                unit        = row[MaterialTable.unit]
            )
        )
    }

    override fun rowsToProductAndDetail(rows: List<ResultRow>): ProductAndDetail {
        return ProductAndDetail(
            id          = rows.first()[ProductTable.productId].toString(),
            name        = rows.first()[ProductTable.name],
            price       = rows.first()[ProductTable.price],
            currency    = rows.first()[ProductTable.currency],
            materials   = rows.map { rowToProductDetail(it) }
        )
    }

    override fun getProductIdByUuid(uuidString: String?) : Long {
        val uuid = uuidService.toValidUuid(uuidString)
        return transaction {
            ProductTable
                .select(ProductTable.id)
                .where { ProductTable.productId eq uuid }
                .single()[ProductTable.id].toLong()
        }
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

        try {
            val product = transaction {
                (ProductTable leftJoin ProductDetailTable leftJoin MaterialTable)
                    .selectAll()
                    .where { ProductTable.productId eq uuid }
                    .orderBy(ProductTable.id to SortOrder.ASC)
                    .groupBy { it[ProductTable.id] }
                    .let { rowsToProductAndDetail(it.values.flatten()) }
            }
            return product
        } catch (ex: Exception) {
            return null
        }
    }

    override fun postProduct(productAndDetail: ProductAndDetail) : ProductAndDetail? {
        val uuid = uuidService.generateUuidV6()

        transaction {
            ProductTable
                .insert{
                    it[productId] = uuid
                    it[name] = productAndDetail.name
                    it[price] = productAndDetail.price
                    it[currency] = productAndDetail.currency
                    it[state] = true
                }

            val theProductId : Long = getProductIdByUuid(uuid)

            ProductDetailTable
                .batchInsert(productAndDetail.materials) { productDetail ->
                        val materialId: Long = materialRepository.getMaterialIdByUuid(productDetail.material.id)

                        this[ProductDetailTable.productId]  = theProductId
                        this[ProductDetailTable.materialId] = materialId
                        this[ProductDetailTable.quantity]   = productDetail.quantity
                }
        }

        return getProductByUuid(uuid.toString())
    }


    override fun putProduct(productAndDetail: ProductAndDetail): ProductAndDetail? {
        val uuid = uuidService.toValidUuid(productAndDetail.id!!)

        transaction {
            ProductTable
                .update(where = { ProductTable.productId eq uuid }) {
                    it[name] = productAndDetail.name
                    it[price] = productAndDetail.price
                    it[currency] = productAndDetail.currency
                }

            val theProductId : Long = getProductIdByUuid(uuid)

            ProductDetailTable
                .batchUpsert(productAndDetail.materials) { productDetail ->
                        val materialId: Long = materialRepository.getMaterialIdByUuid(productDetail.material.id)

                        this[ProductDetailTable.productId] = theProductId
                        this[ProductDetailTable.materialId] = materialId
                        this[ProductDetailTable.quantity] = productDetail.quantity
                    }
        }

        return getProductByUuid(productAndDetail.id)
    }

    override fun deleteProduct(id: String): ProductAndDetail? {
        val product = getProductByUuid(id)

        transaction {
            ProductTable
                .update(where = { ProductTable.productId eq uuidService.toValidUuid(id) }) {
                    it[state] = false
                }
        }

        return product
    }
}