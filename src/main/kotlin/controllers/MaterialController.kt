package com.ipeasa.controllers

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.dtos.material.MaterialDtoU
import com.ipeasa.exceptions.InvalidArgumentException
import com.ipeasa.exceptions.InvalidUuidException
import com.ipeasa.exceptions.ObjectNotFoundException
import com.ipeasa.services.MaterialService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.materialRoutes(materialService: MaterialService) {
    route("/material") {
        get {
            val pageSize : Int? = call.request.queryParameters["pageSize"]?.trim()?.toIntOrNull()
            val page : Long?    = call.request.queryParameters["page"]?.trim()?.toLongOrNull()
            if (pageSize !== null && page !== null) {
                call.respond(materialService.readAllMaterials(pageSize, page))
            }
        }

        get("/search-by-name") {
            val name : String?  = call.request.queryParameters["name"]?.trim()
            val pageSize : Int? = call.request.queryParameters["pageSize"]?.trim()?.toIntOrNull()
            val page : Long?    = call.request.queryParameters["page"]?.trim()?.toLongOrNull()

            if (!name.isNullOrEmpty() && pageSize !== null && page !== null) {
                val materialList : List<Material> = materialService.readMaterialByName(name, pageSize, page)

                if (materialList.isNotEmpty()) {
                    call.respond(materialList)
                } else {
                    throw ObjectNotFoundException(message = "nombre")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Debe ingresar un nombre válido")
            }
        }

        get("/search-by-id") {
            val id : String? = call.request.queryParameters["id"]?.trim()

            if (!id.isNullOrEmpty()) {
                val material : Material? = materialService.readMaterialById(id)

                if (material !== null) {
                    call.respond(material)
                } else {
                    throw InvalidUuidException(message = "Material con id $id no encontrado")
                }
            } else {
                throw InvalidUuidException(message = "El id esta vacío")
            }
        }

        post {
            val material = materialService.createMaterial(call.receive<MaterialDtoC>())

            if (material !== null) {
                call.respond(material)
            } else {
                throw InvalidArgumentException("material")
            }
        }

        put {
            val material = materialService.updateMaterial(call.receive<MaterialDtoU>())

            if (material !== null) {
                call.respond(material)
            } else {
                throw InvalidArgumentException("material")
            }
        }

        delete {
            val id = call.request.queryParameters["id"]?.trim()

            if (!id.isNullOrEmpty()) {
                val material = materialService.deleteMaterial(id)
                call.respond(HttpStatusCode.OK, material!!)
            } else {
                throw InvalidUuidException(message = "El id esta vacío")
            }
        }
    }
}