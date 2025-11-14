package com.example.aplicacionmoviliot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.aplicacionmoviliot.screens.ControlScreen
import com.example.aplicacionmoviliot.screens.DashboardScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppNav() }
    }
}

@Composable
fun AppNav() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(navController = nav, startDestination = "dashboard") {
            composable("dashboard") {
                DashboardScreen(onGoToControl = { nav.navigate("control") })
            }
            composable("control") {
                ControlScreen(onBack = { nav.popBackStack() })
            }
        }
    }
}
