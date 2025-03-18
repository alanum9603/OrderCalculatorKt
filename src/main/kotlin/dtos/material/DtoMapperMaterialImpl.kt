package com.ipeasa.dtos.material

import com.ipeasa.ddds.Material

class DtoMapperMaterialImpl : DtoMapperMaterial {
    override fun materialDtoCToMaterial(materialDtoC: MaterialDtoC) : Material {
        return Material(
            name        = materialDtoC.name,
            unit        = materialDtoC.unit,
            price       = materialDtoC.price,
            currency    = materialDtoC.currency
        )
    }

    override fun materialDtoUToMaterial(materialDtoU: MaterialDtoU) : Material {
        return Material(
            id          = materialDtoU.id,
            name        = materialDtoU.name,
            unit        = materialDtoU.unit,
            price       = materialDtoU.price,
            currency    = materialDtoU.currency
        )
    }
}