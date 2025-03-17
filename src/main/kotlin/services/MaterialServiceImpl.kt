package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.dtos.material.MaterialDtoU
import com.ipeasa.repositories.MaterialRepository

class MaterialServiceImpl(
    private val materialRepository: MaterialRepository
) : MaterialService {
    override fun readAllMaterials(pageSize : Int, page : Long): List<Material> {
        return materialRepository.getAllMaterials(pageSize, page)
    }

    override fun readMaterialByName(name: String, pageSize : Int, page : Long): List<Material> {
        return materialRepository.getMaterialsByName(name, pageSize, page)
    }

    override fun readMaterialById(id: String): Material? {
        return materialRepository.getMaterialByUuid(id)
    }

    override fun createMaterial(materialDtoC: MaterialDtoC): Material? {
        return materialRepository.postMaterial(
            Material(
                name        = materialDtoC.name,
                unit        = materialDtoC.unit,
                price       = materialDtoC.price,
                currency    = materialDtoC.currency
            )
        )
    }

    override fun updateMaterial(materialDtoU: MaterialDtoU): Material? {
        return materialRepository.putMaterial(
            Material(
                id          = materialDtoU.id,
                name        = materialDtoU.name,
                unit        = materialDtoU.unit,
                price       = materialDtoU.price,
                currency    = materialDtoU.currency
            )
        )
    }

    override fun deleteMaterial(id : String): Material? {
        return materialRepository.deleteMaterial(id)
    }
}