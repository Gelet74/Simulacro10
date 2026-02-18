package com.example.simulacro10.datos

import android.content.Context
import com.example.simulacro10.conexion.ConexionBD
import com.example.simulacro10.conexion.ServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {

    val simulacroRepositorioBD : SimulacroDatosBD
    val simulacroRepositorioApi: SimulacroApi
}

class PlantillaContenedorApp (private val context: Context) : ContenedorApp{
    private val baseUrl = "http://192.168.0.25:3000"

    private val json = Json {ignoreUnknownKeys = true}

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: ServicioApi by lazy{
        retrofit.create(ServicioApi::class.java)
    }
    override val simulacroRepositorioApi: SimulacroApi by lazy{
        ConexionSimulacroRepositorioAPI(servicioRetrofit)
    }
    override val simulacroRepositorioBD: SimulacroDatosBD by lazy {
        ConexionSimulacroRepositorioBD(ConexionBD.obtenerBD(context).simulacroDao())
    }
}