package com.ipeasa

import io.ktor.server.application.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*

fun Application.configureDatabase() {
    try {
        Database.connect(
            url = "jdbc:mysql://localhost:3306/backend-order-register",
            user = "db-order-register",
            password = "db-test-369",
            driver = "com.mysql.cj.jdbc.Driver",
        )
    } catch (e : ExposedSQLException) {
        println("Error al conectar a la base de datos: ${e.message}")
        throw e // Relanzar la excepción para detener la aplicación
    }
}