package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.models.MaterialTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class MaterialRepositoryImpl : MaterialRepository {

    private fun rowToMaterial(row : ResultRow) : Material {
        return Material(
            materialId = row[MaterialTable.materialId].toString(),
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

    override fun getMaterialByName(name: String): List<Material> {
        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.name like "%$name%" }
                .map { rowToMaterial(it) }
        }
    }

    override fun getMaterialByUuid(id: String): Material? {
        val uuid : UUID = UUID.fromString(id)

        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.materialId eq uuid }
                .map { rowToMaterial(it) }
                .firstOrNull()
        }
    }
}