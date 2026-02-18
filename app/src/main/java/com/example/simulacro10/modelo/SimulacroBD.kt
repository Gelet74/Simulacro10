package com.example.simulacro10.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SimulacroBD")
data class SimulacroBD (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serie: String="",
    val temporada: Int = 0
)