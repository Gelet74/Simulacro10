package com.example.simulacro10.ui

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.simulacro10.PlantillaAplicacion
import com.example.simulacro10.datos.SimulacroApi
import com.example.simulacro10.datos.SimulacroDatosBD
import com.example.simulacro10.modelo.SimulacroBD
import com.example.simulacro10.modelo.Usuario
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface SimulacroUIStateBD {
    data class ObtenerSerieExito (val simulacroBD: SimulacroBD) : SimulacroUIStateBD

    data class ObtenerTodasExito (val simulacroBD: List<SimulacroBD>) : SimulacroUIStateBD

    object ActualizarExito: SimulacroUIStateBD
    object Error: SimulacroUIStateBD
    object Cargando: SimulacroUIStateBD
}

sealed interface SimulacroUIStateApi {
    data class ObtenerExitoUsuario(val usuario: Usuario): SimulacroUIStateApi
    data class ActualizarExitoUsuario(val usuario: Usuario): SimulacroUIStateApi

    object Error: SimulacroUIStateApi
    object Cargando: SimulacroUIStateApi
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class PlantillaViewModel (
    private val plantillaRepositorioBD: SimulacroDatosBD,
    private val plantillaRepositorioAPI: SimulacroApi) : ViewModel() {

    var bdUIState : SimulacroUIStateBD by mutableStateOf(SimulacroUIStateBD.Cargando)
    var apiUIState : SimulacroUIStateApi by mutableStateOf(SimulacroUIStateApi.Cargando)

    init {
        obtenerUsuario()
    }

    fun obtenerUsuario(){
        viewModelScope.launch{
            apiUIState = SimulacroUIStateApi.Cargando
            apiUIState = try {
                val usuario = plantillaRepositorioAPI.obtenerUsuario()
                SimulacroUIStateApi.ObtenerExitoUsuario(usuario = usuario)
            }catch (e: IOException) {
                SimulacroUIStateApi.Error
            }catch (e: HttpException){
                SimulacroUIStateApi.Error
            }
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            val id = usuario.id
            apiUIState = SimulacroUIStateApi.Cargando
            apiUIState = try {
                val usuarioActualizado = plantillaRepositorioAPI.actualizarUsuario(id, usuario)
                SimulacroUIStateApi.ActualizarExitoUsuario(usuarioActualizado)
            } catch (e: IOException) {
                SimulacroUIStateApi.Error
            } catch (e: HttpException) {
                SimulacroUIStateApi.Error
            }
        }
    }

    fun obtenerSerieBD(id:Int) {
        viewModelScope.launch {
            bdUIState = SimulacroUIStateBD.Cargando
            bdUIState = try {
                val serieBD = plantillaRepositorioBD.obtenerSerie(id)
                SimulacroUIStateBD.ObtenerSerieExito(serieBD)
            }catch (e: Exception) {
                SimulacroUIStateBD.Error
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as PlantillaAplicacion)
                val plantillaRepositorioBD = aplicacion.contenedor.simulacroRepositorioBD
                val plantillaRepositorioAPI = aplicacion.contenedor.simulacroRepositorioApi
                PlantillaViewModel(plantillaRepositorioBD= plantillaRepositorioBD, plantillaRepositorioAPI= plantillaRepositorioAPI)
            }
        }
    }
}