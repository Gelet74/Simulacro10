package com.example.simulacro10.ui.pantallas


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simulacro10.R
import com.example.simulacro10.modelo.Usuario
import com.example.simulacro10.ui.PlantillaViewModel
import com.example.simulacro10.ui.SimulacroUIStateApi

@Composable
fun PantallaPerfil (
    apiUIState: SimulacroUIStateApi,
    onUsuarioObtenido: () -> Unit,
    onUsuarioPulsado: (Usuario) -> Unit,
    modifier: Modifier = Modifier
) {
    when ( apiUIState) {
        is SimulacroUIStateApi.Cargando -> PantallaCargando()
        is SimulacroUIStateApi.Error -> PantallaError()

        is SimulacroUIStateApi.ObtenerExitoUsuario -> PantallaListarUsuario (
            usuario = apiUIState.usuario,
            onUsuarioPulsado = onUsuarioPulsado,
            modifier = modifier.fillMaxWidth()
        )
        is SimulacroUIStateApi.ActualizarExitoUsuario -> onUsuarioObtenido()
    }
}

@Composable
fun PantallaListarUsuario(
    usuario: Usuario,
    onUsuarioPulsado: (Usuario) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clickable { onUsuarioPulsado(usuario) }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = usuario.nombre)
            Text(text = usuario.fechanacimiento)
            usuario.series.forEach { serie ->
                Text(text = serie.titulo)
            }

        }
    }
}


@Composable
fun PantallaCargando(modifier: Modifier= Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = "Cargando"
    )
}
@Composable
fun PantallaError(modifier: Modifier= Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.error),
        contentDescription = "Cargando"
    )
}