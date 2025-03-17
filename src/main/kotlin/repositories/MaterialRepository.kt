package com.ipeasa.repositories

import com.ipeasa.ddds.Material

interface MaterialRepository {
    fun getAllMaterials() : List<Material>

    fun getMaterialByName(name : String) : List<Material>

    fun getMaterialByUuid(id : String) : Material?

    fun postMaterial(material: Material) : Material?

    fun putMaterial(material: Material) : Material?

    fun deleteMaterial(id: String) : Material?
}