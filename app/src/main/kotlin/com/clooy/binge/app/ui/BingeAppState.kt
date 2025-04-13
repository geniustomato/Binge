package com.clooy.binge.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberBingeAppState(
    navController: NavHostController = rememberNavController()
): BingeAppState {
    return remember(navController) {
        BingeAppState(navController = navController)
    }
}

class BingeAppState(
    val navController: NavHostController
)
