package com.example.aplicacionmoviliot.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aplicacionmoviliot.Firebase.DeviceState
import com.example.aplicacionmoviliot.Firebase.SensorData
import com.google.firebase.database.*

@Composable
fun DashboardScreen(onGoToControl: () -> Unit) {
    var sensor by remember { mutableStateOf(SensorData()) }
    var state  by remember { mutableStateOf(DeviceState()) }
    val rtdb = FirebaseDatabase.getInstance()

    LaunchedEffect(Unit) {
        rtdb.getReference("sensor_data").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(s: DataSnapshot) { sensor = s.getValue(SensorData::class.java) ?: SensorData() }
            override fun onCancelled(error: DatabaseError) {}
        })
        rtdb.getReference("device_state").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(s: DataSnapshot) { state = s.getValue(DeviceState::class.java) ?: DeviceState() }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Dashboard", style = MaterialTheme.typography.headlineSmall)
        Divider()
        Text("Nivel de gas (raw): ${sensor.raw_value}")
        Text("Voltaje: ${sensor.voltage} V")
        Text("Compuerta: ${state.door_state}")
        Text("Modo: ${state.mode}")
        Text("Umbral actual: ${state.threshold}")
        if (state.last_event.isNotBlank()) Text("Último evento: ${state.last_event}")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onGoToControl, modifier = Modifier.fillMaxWidth()) { Text("Ir a Control") }
    }
}

