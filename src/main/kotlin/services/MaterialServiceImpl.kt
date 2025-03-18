package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.DtoMapperMaterial
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.dtos.material.MaterialDtoU
import com.ipeasa.repositories.MaterialRepository

class MaterialServiceImpl(
    private val materialRepository: MaterialRepository,
    private val dtoMapperMaterial: DtoMapperMaterial
) : MaterialService {
    override fun readAllMaterials(pageSize : Int, page : Long): List<Material> {
        return materialRepository.getAllMaterials(pageSize, page)
    }

    override fun readMaterialsByName(name: String, pageSize : Int, page : Long): List<Material> {
        return materialRepository.getMaterialsByName(name, pageSize, page)
    }

    override fun readMaterialById(id: String): Material? {
        return materialRepository.getMaterialByUuid(id)
    }

    override fun createMaterial(materialDtoC: MaterialDtoC): Material? {
        return materialRepository.postMaterial(
            dtoMapperMaterial.materialDtoCToMaterial(materialDtoC)
        )
    }

    override fun updateMaterial(materialDtoU: MaterialDtoU): Material? {
        return materialRepository.putMaterial(
            dtoMapperMaterial.materialDtoUToMaterial(materialDtoU)
        )
    }

    override fun deleteMaterial(id : String): Material? {
        return materialRepository.deleteMaterial(id)
    }
}