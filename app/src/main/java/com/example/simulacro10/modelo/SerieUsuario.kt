package com.example.simulacro10.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerieUsuario (
    @SerialName(value="titulo")
    val titulo: String="",
    @SerialName(value="temporadas")
    val temporadas:List<Int> = emptyList(),
    @SerialName(value = "puntuacion")
    val puntuacion: Int = 0
)
