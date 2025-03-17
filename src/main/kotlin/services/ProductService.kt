package com.ipeasa.services

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail


interface ProductService {
    fun readAllProducts(pageSize : Int, page : Long) : List<Product>

    fun readProductsByName(name : String, pageSize : Int, page : Long) : List<Product>

    fun readProductById(id : String) : ProductAndDetail?

    // fun createProduct(materialDtoC: ProductDtoC) : Product?

    // fun updateProduct(materialDtoU: ProductDtoU) : Product?

    fun deleteProduct(id : String) : Product?
}