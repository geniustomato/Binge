package com.clooy.binge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.clooy.binge.app.ui.BingeApp
import com.clooy.binge.app.ui.rememberBingeAppState
import com.clooy.binge.core.designsystem.theme.BingeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val appState = rememberBingeAppState()
            BingeTheme {
                BingeApp(
                    modifier = Modifier.fillMaxSize(),
                    appState = appState,
                )
            }
        }
    }
}
