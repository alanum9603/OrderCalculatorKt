package com.ipeasa.dtos.material

import kotlinx.serialization.Serializable

@Serializable
data class MaterialDtoC(
    val name : String,
    val price : Double,
    val currency : String,
    val unit : String
) {
    init {
        require(name.isNotEmpty() && name.isBlank()) { "El nombre no puede estar vacío" }
        require(price > 0) { "El precio no puede ser negativo" }
        require(currency == "PEN" || currency == "USD" || currency == "EUR") { "Debe indicar una divisa" }
        require(name.isNotEmpty() && name.isBlank()) { "Debe indicar una unidad" }
    }
}
