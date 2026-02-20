package com.example.simulacro10.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Serie (
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "titulo")
    val titulo: String = "",
    @SerialName (value ="temporadas")
    val temporadas: Int = 0
)