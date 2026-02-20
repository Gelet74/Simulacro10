package com.example.simulacro10.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usuario (
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "nombre")
    val nombre: String = "",
    @SerialName(value="fechanacimiento")
    val fechanacimiento: String ="",
    @SerialName(value="series")
    val series: List<SerieUsuario>
)