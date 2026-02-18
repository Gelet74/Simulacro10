package com.example.simulacro10.conexion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simulacro10.dao.SimulacroDao
import com.example.simulacro10.modelo.SimulacroBD

@Database(entities = [SimulacroBD::class],version = 1, exportSchema = false)
abstract class ConexionBD : RoomDatabase() {

    abstract fun simulacroDao() : SimulacroDao

    companion object {
        @Volatile
        private var Instance: ConexionBD? = null

        fun obtenerBD(context: Context): ConexionBD {
            return Instance ?: synchronized(lock = this) {
                Room.databaseBuilder(context, ConexionBD::class.java, "simulacrodb")
                    .build()
                    .also {Instance = it }
            }
        }
    }
}