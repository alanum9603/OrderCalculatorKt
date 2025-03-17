package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.models.MaterialTable
import com.ipeasa.utils.UuidService
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class MaterialRepositoryImpl(
    private val uuidService: UuidService
) : MaterialRepository {

    private fun rowToMaterial(row : ResultRow) : Material {
        return Material(
            id = row[MaterialTable.materialId].toString(),
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
                .where { MaterialTable.state eq true }
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
                .andWhere { MaterialTable.state eq true }
                .map { rowToMaterial(it) }
        }
    }

    override fun getMaterialByUuid(id: String): Material? {
        val uuid : UUID = uuidService.toValidUuid(id)

        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.materialId eq uuid }
                .andWhere { MaterialTable.state eq true }
                .map { rowToMaterial(it) }
                .firstOrNull()
        }
    }

    override fun postMaterial(material: Material): Material? {

        val uuid = uuidService.generateUuidV6()

        transaction {
            try {

                MaterialTable
                    .insert {
                        it[materialId] = uuid
                        it[name] = material.name
                        it[price] = material.price
                        it[unit] = material.unit
                        it[currency] = material.currency
                    }

                getMaterialByUuid(uuid.toString())
            } catch (ex: Exception) {
                null
            }
        }

        return getMaterialByUuid(uuid.toString())
    }

    override fun putMaterial(material: Material): Material? {
        val uuid = uuidService.toValidUuid(material.id!!)

        transaction {
            MaterialTable
                .update(where = { MaterialTable.materialId eq uuid }) {
                    it[materialId] = uuid
                    it[name] = material.name
                    it[price] = material.price
                    it[unit] = material.unit
                    it[currency] = material.currency
                }
        }

        return getMaterialByUuid(material.id!!)
    }

    override fun deleteMaterial(id: String): Material? {
        return transaction {
            try {
                val uuid = uuidService.toValidUuid(id)

                MaterialTable
                    .update(where = { MaterialTable.materialId eq uuid }) {
                        it[state] = false
                    }

                getMaterialByUuid(uuid.toString())
            } catch (ex : Exception) {
                null
            }
        }
    }
}