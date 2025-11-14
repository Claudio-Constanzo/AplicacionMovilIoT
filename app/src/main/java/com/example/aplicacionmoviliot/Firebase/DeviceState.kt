package com.example.aplicacionmoviliot.Firebase


data class SensorData(
    var raw_value: Int = 0,
    var voltage: Double = 0.0,
    var timestamp: Long = 0
)

data class DeviceState(
    var door_state: String = "OPEN",
    var mode: String = "MANUAL",
    var threshold: Int = 400,
    var last_event: String = ""
)
