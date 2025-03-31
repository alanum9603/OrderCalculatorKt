package com.ipeasa.repositories

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.ddds.ProductDetail
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

interface ProductRepository {
    fun rowToProduct(row: ResultRow): Product

    fun rowToProductDetail(row: ResultRow) : ProductDetail

    fun rowsToProductAndDetail(rows: List<ResultRow>): ProductAndDetail

    fun getProductIdByUuid(uuidString: String?) : Long

    fun getAllProducts(pageSize : Int, page : Long) : List<Product>

    fun getProductsByName(name : String, pageSize : Int, page : Long) : List<Product>

    fun getProductByUuid(id : String) : ProductAndDetail?

    fun postProduct(productAndDetail: ProductAndDetail) : ProductAndDetail?

    fun putProduct(productAndDetail: ProductAndDetail): ProductAndDetail?

    fun deleteProduct(id: String) : ProductAndDetail?
}