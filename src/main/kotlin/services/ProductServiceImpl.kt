package com.ipeasa.services

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.dtos.product.DtoMapperProduct
import com.ipeasa.dtos.product.ProductDtoC
import com.ipeasa.dtos.product.ProductDtoU
import com.ipeasa.repositories.ProductRepository

class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val dtoMapperProduct: DtoMapperProduct
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
        return productRepository.postProduct(dtoMapperProduct.productDtoCToProduct(productDtoC))
    }

    override fun updateProduct(productDtoU: ProductDtoU): ProductAndDetail? {
        return productRepository.putProduct(dtoMapperProduct.productDtoUToProduct(productDtoU))
    }

    override fun deleteProduct(id: String): ProductAndDetail? {
        return productRepository.deleteProduct(id)
    }
}