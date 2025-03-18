package com.ipeasa.dtos.product

import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.ddds.ProductDetail

interface DtoMapperProduct {
    fun productDetailDtoCToProductDetail(productDetailDtoC: ProductDetailDtoC) : ProductDetail

    fun productDtoCToProduct(productDtoC: ProductDtoC) : ProductAndDetail

    fun productDetailDtoUToProductDetail(productDetailDtoU: ProductDetailDtoU) : ProductDetail

    fun productDtoUToProduct(productDtoU: ProductDtoU) : ProductAndDetail
}