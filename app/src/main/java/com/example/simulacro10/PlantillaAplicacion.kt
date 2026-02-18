package com.example.simulacro10

import android.app.Application
import com.example.simulacro10.datos.ContenedorApp
import com.example.simulacro10.datos.PlantillaContenedorApp

class PlantillaAplicacion : Application(){

    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = PlantillaContenedorApp(this)
    }
}