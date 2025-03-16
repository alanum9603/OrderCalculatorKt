package com.ipeasa.controllers

import com.ipeasa.ddds.Material
import com.ipeasa.dtos.material.MaterialDtoC
import com.ipeasa.exceptions.InvalidUuidException
import com.ipeasa.exceptions.NotFoundException
import com.ipeasa.services.MaterialService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.materialRoutes(materialService: MaterialService) {
    route("/material") {
        get {
            call.respond(materialService.readAllMaterials())
        }

        get("/search-by-name/{name?}") {
            val name : String? = call.parameters["name"]?.trim()

            if (name.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Debe ingresar un nombre válido")
            } else {
                val materialList : List<Material> = materialService.readMaterialByName(name)

                if (materialList.isNotEmpty()) {
                    call.respond(materialList)
                } else {
                    throw NotFoundException(message = "nombre")
                }
            }
        }

        get("/search-by-id/{id?}") {
            val id : String? = call.parameters["id"]?.trim()

            if (id.isNullOrEmpty()) {
                throw InvalidUuidException(message = "El id esta vacío")
            } else {
                val material : Material? = materialService.readMaterialById(id)

                if (material !== null) {
                    call.respond(material)
                } else {
                    throw InvalidUuidException(message = "Material con id $id no encontrado")
                }
            }
        }

        post {
            val material = materialService.postMaterial(call.receive<MaterialDtoC>())

            if (material !== null) {
                call.respond(material)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Debe pasar todos los argumentos del material")
            }
        }
    }
}