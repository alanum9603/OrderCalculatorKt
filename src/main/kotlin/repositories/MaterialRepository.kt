package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import org.jetbrains.exposed.sql.ResultRow

interface MaterialRepository {
    fun rowToMaterial(row : ResultRow) : Material

    fun isExistsMaterialName(material: Material) : Boolean

    fun getMaterialIdByUuid(uuidString: String?) : Long

    fun getAllMaterials(pageSize : Int, page : Long) : List<Material>

    fun getMaterialsByName(name : String, pageSize : Int, page : Long) : List<Material>

    fun getMaterialByUuid(id : String) : Material?

    fun postMaterial(material: Material) : Material?

    fun putMaterial(material: Material) : Material?

    fun deleteMaterial(id: String) : Material?
}