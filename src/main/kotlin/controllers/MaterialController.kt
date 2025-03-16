package com.ipeasa.controllers

import com.ipeasa.ddds.Material
import com.ipeasa.services.MaterialService
import io.ktor.http.*
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
                    call.respond(HttpStatusCode.NotFound, "Material con nombre $name no encontrado")
                }
            }
        }

        get("/search-by-id/{id?}") {
            val id : String? = call.parameters["id"]?.trim()

            if (id.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Debe ingresar un id válido")
            } else {
                val material : Material? = materialService.readMaterialById(id)

                if (material !== null) {
                    call.respond(material)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Material con id $id no encontrado")
                }
            }
        }
    }
}