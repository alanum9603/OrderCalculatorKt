package com.ipeasa.dtos.material

import com.ipeasa.ddds.Material

interface DtoMapperMaterial {
    fun materialDtoCToMaterial(materialDtoC: MaterialDtoC) : Material

    fun materialDtoUToMaterial(materialDtoU: MaterialDtoU) : Material
}