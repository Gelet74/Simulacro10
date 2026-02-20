package com.example.simulacro10.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simulacro10.R
import com.example.simulacro10.modelo.Ruta
import com.example.simulacro10.ui.pantallas.PantallaPerfil
import com.example.simulacro10.ui.pantallas.PantallaSeries
import com.example.simulacro10.ui.pantallas.PantallaVer

enum class PantallasBar(@StringRes val titulo: Int){
    Perfil(titulo= R.string.perfil),
    Series(titulo= R.string.series),
    Ver (titulo=R.string.ver)
}

val listaRutas = listOf(
    Ruta(
        PantallasBar.Perfil.titulo,
        PantallasBar.Perfil.name,
        iconoLleno = Icons.Filled.AccountCircle,
        iconoVacio = Icons.Outlined.AccountCircle
    ),
    Ruta(
        PantallasBar.Series.titulo,
        PantallasBar.Series.name,
        iconoLleno = Icons.Filled.ThumbUp,
        iconoVacio = Icons.Outlined.ThumbUp
    ),
    Ruta(
        PantallasBar.Ver.titulo,
        PantallasBar.Ver.name,
        iconoLleno = Icons.Filled.PlayArrow,
        iconoVacio = Icons.Outlined.PlayArrow
    )
)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PantallaPrincipal() {
    val navController = rememberNavController()
    val viewModel: PlantillaViewModel = viewModel(factory = PlantillaViewModel.Factory)

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = PantallasBar.Perfil.name,
            modifier = Modifier.padding(padding)
        ) {
            composable(PantallasBar.Perfil.name) {
                PantallaPerfil(
                    apiUIState = viewModel.apiUIState,
                    onUsuarioObtenido = { },
                    onUsuarioPulsado= { }
                )
            }
            composable(PantallasBar.Series.name) {
                PantallaSeries()
            }
            composable(PantallasBar.Ver.name) {
                PantallaVer(apiUIStateSeries = viewModel.apiUIStateSerie,
                            onCargarSeries ={viewModel.obtenerSeries()},
                            onSeriePulsada =  {serie ->})
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val destinoActual = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        listaRutas.forEach { ruta ->
            NavigationBarItem(
                selected = destinoActual == ruta.ruta,
                onClick = {
                    navController.navigate(ruta.ruta) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (destinoActual == ruta.ruta) ruta.iconoLleno else ruta.iconoVacio,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(ruta.nombre)) }
            )
        }
    }
}















