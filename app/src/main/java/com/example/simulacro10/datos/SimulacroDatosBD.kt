package com.example.simulacro10.datos

import com.example.simulacro10.dao.SimulacroDao
import com.example.simulacro10.modelo.SimulacroBD


interface SimulacroDatosBD {
    suspend fun obtenerSerie(id: Int): SimulacroBD
    suspend fun obtenerTodasSeries (): List<SimulacroBD>

}

class ConexionSimulacroRepositorioBD(
    private val simulacroDao : SimulacroDao
) : SimulacroDatosBD{
    override suspend fun obtenerSerie(id: Int) = simulacroDao.obtenerSerie(id)
    override suspend fun obtenerTodasSeries(): List<SimulacroBD> = simulacroDao.obtenerTodasSeries()
}