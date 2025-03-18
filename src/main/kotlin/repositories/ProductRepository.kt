package com.ipeasa.repositories

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.dtos.product.ProductDtoC

interface ProductRepository {
    fun getAllProducts(pageSize : Int, page : Long) : List<Product>

    fun getProductsByName(name : String, pageSize : Int, page : Long) : List<Product>

    fun getProductByUuid(id : String) : ProductAndDetail?

    fun postProduct(productDtoC: ProductDtoC): ProductAndDetail?

    fun putProduct(product: Product) : Product?

    fun deleteProduct(id: String) : Product?
}