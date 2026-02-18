package com.example.simulacro10.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simulacro10.modelo.SimulacroBD

@Dao
interface SimulacroDao {

    @Query ("SELECT * from SimulacroBD WHERE id = :id")
    suspend fun obtenerSerie(id: Int): SimulacroBD

    @Query ("SELECT * from SimulacroBD ORDER BY serie ASC")
    suspend fun obtenerTodasSeries(): List<SimulacroBD>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar (simulacroBD: SimulacroBD)

    @Update
    suspend fun actualizar(simulacroBD: SimulacroBD)
}