package com.ipeasa.services

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.dtos.product.ProductDtoC
import com.ipeasa.dtos.product.ProductDtoU


interface ProductService {
    fun readAllProducts(pageSize : Int, page : Long) : List<Product>

    fun readProductsByName(name : String, pageSize : Int, page : Long) : List<Product>

    fun readProductById(id : String) : ProductAndDetail?

    fun createProduct(productDtoC: ProductDtoC) : ProductAndDetail?

    fun updateProduct(productDtoU: ProductDtoU) : Product?

    fun deleteProduct(id : String) : Product?
}