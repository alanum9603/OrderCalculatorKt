package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.repositories.MaterialRepository

class MaterialServiceImpl(
    private val materialRepository: MaterialRepository
) : MaterialService {
    override fun readAllMaterials(): List<Material> {
        return materialRepository.getAllMaterials()
    }

    override fun readMaterialByName(name: String): List<Material> {
        return materialRepository.getMaterialByName(name)
    }

    override fun readMaterialById(id: String): Material? {
        return materialRepository.getMaterialByUuid(id)
    }
}