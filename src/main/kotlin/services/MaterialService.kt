package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.dtos.material.MaterialDtoU

interface MaterialService {
    fun readAllMaterials() : List<Material>

    fun readMaterialByName(name : String) : List<Material>

    fun readMaterialById(id : String) : Material?

    fun postMaterial(materialDtoC: MaterialDtoC) : Material?

    fun putMaterial(materialDtoU: MaterialDtoU) : Material?

    fun deleteMaterial(id : String) : Material?
}