package com.ipeasa.repositories

import com.ipeasa.ddds.Material

interface MaterialRepository {
    fun getAllMaterials(pageSize : Int, page : Long) : List<Material>

    fun getMaterialsByName(name : String, pageSize : Int, page : Long) : List<Material>

    fun getMaterialByUuid(id : String) : Material?

    fun postMaterial(material: Material) : Material?

    fun putMaterial(material: Material) : Material?

    fun deleteMaterial(id: String) : Material?
}