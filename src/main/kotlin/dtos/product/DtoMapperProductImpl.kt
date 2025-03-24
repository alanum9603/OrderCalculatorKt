package com.ipeasa.dtos.product

import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.ddds.ProductDetail
import com.ipeasa.exceptions.ObjectNotFoundException
import com.ipeasa.repositories.MaterialRepository

class DtoMapperProductImpl(
    private val materialRepository: MaterialRepository
) : DtoMapperProduct {
    override fun productDetailDtoCToProductDetail(productDetailDtoC: ProductDetailDtoC) : ProductDetail {
        return ProductDetail(
            quantity = productDetailDtoC.quantity,
            material = materialRepository.getMaterialByUuid(productDetailDtoC.materialId)
                ?: throw ObjectNotFoundException("material con id ${productDetailDtoC.materialId}")
        )
    }

    override fun productDtoCToProduct(productDtoC: ProductDtoC) : ProductAndDetail {
        return ProductAndDetail(
            name = productDtoC.name,
            price = productDtoC.price,
            currency = productDtoC.currency,
            materials = productDtoC.materials.map { productDetailDtoCToProductDetail(it) }
        )
    }

    override fun productDetailDtoUToProductDetail(productDetailDtoU: ProductDetailDtoU) : ProductDetail {
        return ProductDetail(
            id = productDetailDtoU.id,
            quantity = productDetailDtoU.quantity,
            material = materialRepository.getMaterialByUuid(productDetailDtoU.materialId)
                ?: throw ObjectNotFoundException("material con id ${productDetailDtoU.materialId}")
        )
    }

    override fun productDtoUToProduct(productDtoU: ProductDtoU) : ProductAndDetail {
        return ProductAndDetail(
            id = productDtoU.id,
            name = productDtoU.name,
            price = productDtoU.price,
            currency = productDtoU.currency,
            materials = productDtoU.materials.map {
                productDetailDtoUToProductDetail(it)
            }
        )
    }
}