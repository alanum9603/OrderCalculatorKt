package com.ipeasa.services

import com.ipeasa.ddds.Material

interface MaterialService {
    fun readAllMaterials() : List<Material>

    fun readMaterialByName(name : String) : List<Material>

    fun readMaterialById(id : String) : Material?
}