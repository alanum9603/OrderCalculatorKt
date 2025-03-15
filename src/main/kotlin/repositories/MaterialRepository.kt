package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.models.MaterialTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

interface MaterialRepository {
    fun getAllMaterials() : List<Material>
}