package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import java.util.UUID

interface MaterialRepository {
    fun getAllMaterials() : List<Material>

    fun getMaterialByName(name : String) : List<Material>

    fun getMaterialByUuid(uuid : String) : Material?
}