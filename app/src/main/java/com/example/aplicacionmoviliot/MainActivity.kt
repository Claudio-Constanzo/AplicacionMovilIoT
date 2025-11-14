package com.example.aplicacionmoviliot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionmoviliot.screens.LoginScreen
import com.example.aplicacionmoviliot.Screens.DashboardScreen
import com.example.aplicacionmoviliot.Screens.ControlScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNav()
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    var isLogged by remember { mutableStateOf(false) }

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = if (isLogged) "dashboard" else "login"
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        isLogged = true
                        navController.navigate("dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("dashboard") {
                DashboardScreen(
                    onGoToControl = { navController.navigate("control") }
                )
            }
            composable("control") {
                ControlScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}


