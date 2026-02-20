package com.example.simulacro10.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simulacro10.modelo.Serie
import com.example.simulacro10.ui.SimulacroUIStateSeries

@Composable
fun PantallaVer(
    apiUIStateSeries: SimulacroUIStateSeries,
    onCargarSeries: () -> Unit,
    onSeriePulsada: (Serie) -> Unit

)
{
    LaunchedEffect(Unit) {
        onCargarSeries()
    }
    when (apiUIStateSeries){
        is SimulacroUIStateSeries.Cargando -> PantallaCargando()
        is SimulacroUIStateSeries.Error -> PantallaError()
        is SimulacroUIStateSeries.Exito -> ListarSeries(apiUIStateSeries, onSeriePulsada = onSeriePulsada)
    }

}

@Composable
fun ListarSeries(
    apiUIStateSeries: SimulacroUIStateSeries.Exito,
    onSeriePulsada: (Serie) -> Unit
) {
    LazyColumn {
        items(apiUIStateSeries.series) { serie ->
            Row(
                modifier = Modifier.clickable {onSeriePulsada(serie)}
            ) {
                Text(text = serie.titulo)

            }
            HorizontalDivider(Modifier.fillMaxWidth(0.8f),
                             thickness = 4.dp)
        }
    }
}