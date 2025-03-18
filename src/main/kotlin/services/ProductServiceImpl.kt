package com.ipeasa.services

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.dtos.product.ProductDtoC
import com.ipeasa.dtos.product.ProductDtoU
import com.ipeasa.models.ProductDetailTable
import com.ipeasa.models.ProductTable
import com.ipeasa.repositories.ProductRepository
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun readAllProducts(pageSize: Int, page: Long): List<Product> {
        return productRepository.getAllProducts(pageSize, page)
    }

    override fun readProductsByName(name: String, pageSize: Int, page: Long): List<Product> {
        return productRepository.getProductsByName(name, pageSize, page)
    }

    override fun readProductById(id: String): ProductAndDetail? {
        return productRepository.getProductByUuid(id)
    }

    override fun createProduct(productDtoC: ProductDtoC): ProductAndDetail? {
        return productRepository.postProduct(productDtoC)
    }

    override fun updateProduct(productDtoU: ProductDtoU): Product? {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: String): Product? {
        TODO("Not yet implemented")
    }
}