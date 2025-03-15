package com.ipeasa.services

import com.ipeasa.ddds.Material
import com.ipeasa.repositories.MaterialRepository
import com.ipeasa.repositories.MaterialRepositoryImpl

class MaterialServiceImpl (
    // private val materialRepository: MaterialRepository
) : MaterialService {
    override fun readAllMaterials(): String {
        return "materialRepository.getAllMaterials()"
    }
}