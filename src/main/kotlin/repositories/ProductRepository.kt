package com.ipeasa.repositories

import com.ipeasa.ddds.Product

interface ProductRepository {
    fun getAllProducts(pageSize : Int, page : Long) : List<Product>

    fun getProductsByName(name : String, pageSize : Int, page : Long) : List<Product>

    fun getProductByUuid(id : String) : Product?

    fun postProduct(material: Product) : Product?

    fun putProduct(material: Product) : Product?

    fun deleteProduct(id: String) : Product?
}