package com.example.aplicacionmoviliot.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aplicacionmoviliot.Firebase.FirebaseHelper

@Composable
fun ControlScreen(onBack: () -> Unit) {
    var mode by remember { mutableStateOf("MANUAL") }
    var threshold by remember { mutableStateOf(400f) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Control", style = MaterialTheme.typography.headlineSmall)
        Divider()

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button({ FirebaseHelper.write("control/command", "OPEN") })  { Text("Abrir")  }
            Button({ FirebaseHelper.write("control/command", "CLOSE") }) { Text("Cerrar") }
        }

        Divider()
        Text("Modo actual: $mode")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button({
                mode = "MANUAL"
                FirebaseHelper.write("control/mode", mode)
            }) { Text("Manual") }
            Button({
                mode = "AUTO"
                FirebaseHelper.write("control/mode", mode)
            }) { Text("Automático") }
        }

        Divider()
        Text("Umbral: ${threshold.toInt()}")
        Slider(value = threshold, onValueChange = { threshold = it }, valueRange = 100f..900f)
        Button({ FirebaseHelper.write("control/threshold", threshold.toInt()) }) { Text("Guardar umbral") }

        Spacer(Modifier.height(12.dp))
        Button(onBack, modifier = Modifier.fillMaxWidth()) { Text("Volver al Dashboard") }
    }
}
