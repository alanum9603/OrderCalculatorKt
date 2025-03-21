package com.ipeasa.repositories

import com.ipeasa.ddds.Material
import com.ipeasa.exceptions.AlreadyExistsException
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

    override fun isExistsMaterialName(material: Material): Boolean {
        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.name eq material.name }
                .count() > 0
        }
    }

    override fun getAllMaterials(pageSize : Int, page : Long) : List<Material> {
        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.state eq true }
                .orderBy(MaterialTable.id to SortOrder.ASC)
                .limit(pageSize).offset((page - 1) * pageSize)
                .map { rowToMaterial(it) }
        }
    }

    override fun getMaterialsByName(name: String, pageSize : Int, page : Long): List<Material> {
        return transaction {
            MaterialTable
                .selectAll()
                .where { MaterialTable.name like "%$name%" }
                .andWhere { MaterialTable.state eq true }
                .orderBy(MaterialTable.id to SortOrder.ASC)
                .limit(pageSize).offset((page - 1) * pageSize)
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
                .orderBy(MaterialTable.id to SortOrder.ASC)
                .map { rowToMaterial(it) }
                .firstOrNull()
        }
    }

    override fun postMaterial(material: Material): Material? {
        if (isExistsMaterialName(material)) {
            val uuid = uuidService.generateUuidV6()

            transaction {
                MaterialTable
                    .insert {
                        it[materialId] = uuid
                        it[name] = material.name
                        it[price] = material.price
                        it[unit] = material.unit
                        it[currency] = material.currency
                        it[state] = true
                    }
            }

            return getMaterialByUuid(uuid.toString())
        } else {
            throw AlreadyExistsException("material con nombre ${material.name}")
        }
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

        return getMaterialByUuid(material.id)
    }

    override fun deleteMaterial(id: String): Material? {
        val uuid = uuidService.toValidUuid(id)
        val material = getMaterialByUuid(id)

        transaction {
            MaterialTable
                .update(where = { MaterialTable.materialId eq uuid }) {
                    it[state] = false
                }
        }

        return material
    }
}