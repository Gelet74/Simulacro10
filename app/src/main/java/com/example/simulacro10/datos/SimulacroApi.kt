package com.example.simulacro10.datos

import com.example.simulacro10.conexion.ServicioApi
import com.example.simulacro10.modelo.Serie
import com.example.simulacro10.modelo.Usuario

interface SimulacroApi {
    suspend fun obtenerSerie(): List<Serie>

    suspend fun actualizarSerie(id: String, serie: Serie): Serie

    suspend fun obtenerUsuario(): Usuario
    suspend fun  actualizarUsuario(id: String, usuario: Usuario) : Usuario
}

class ConexionSimulacroRepositorioAPI(
    private val servicioApi: ServicioApi
) : SimulacroApi {
    override suspend fun obtenerSerie(): List<Serie> = servicioApi.obtenerSeries()

    override suspend fun actualizarSerie(id: String, serie: Serie): Serie=servicioApi.actualizarSerie(id, serie)

    override suspend fun obtenerUsuario(): Usuario= servicioApi.obtenerUsuario()

    override suspend fun actualizarUsuario(id: String, usuario: Usuario): Usuario = servicioApi.actualizarUsuario(id, usuario)
}