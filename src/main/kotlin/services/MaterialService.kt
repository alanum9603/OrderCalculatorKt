package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.dtos.material.MaterialDtoU

interface MaterialService {
    fun readAllMaterials(pageSize : Int, page : Long) : List<Material>

    fun readMaterialsByName(name : String, pageSize : Int, page : Long) : List<Material>

    fun readMaterialById(id : String) : Material?

    fun createMaterial(materialDtoC: MaterialDtoC) : Material?

    fun updateMaterial(materialDtoU: MaterialDtoU) : Material?

    fun deleteMaterial(id : String) : Material?
}