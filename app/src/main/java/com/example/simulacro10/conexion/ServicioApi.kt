package com.example.simulacro10.conexion

import com.example.simulacro10.modelo.Serie
import com.example.simulacro10.modelo.Usuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServicioApi {

    @GET("usuario")
    suspend fun obtenerUsuario (): Usuario

    @PUT ("usuario/{id}")
    suspend fun actualizarUsuario(
        @Path ("id") id:String,
        @Body usuario:Usuario
    ) : Usuario

    @GET(value="series")
    suspend fun obtenerSeries(): List<Serie>

    @PUT(value="series/{id}")
    suspend fun actualizarSerie(
        @Path (value="id") id:String,
        @Body serie: Serie
    ) : Serie
}