package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.models.MaterialTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MaterialRepositoryImpl : MaterialRepository {

    private fun rowToMaterial(row : ResultRow) : Material {
        return Material(
            materialId = row[MaterialTable.materialId],
            name = row[MaterialTable.name],
            price = row[MaterialTable.price],
            currency = row[MaterialTable.currency],
            unit = row[MaterialTable.unit]
        )
    }

    override fun getAllMaterials() : List<Material> {
        return transaction {
            MaterialTable
                .selectAll()
                .map {
                    rowToMaterial(it)
                }
        }
    }
}