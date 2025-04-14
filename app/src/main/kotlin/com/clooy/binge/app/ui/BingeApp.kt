package com.clooy.binge.app.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clooy.binge.core.designsystem.component.BingeBackground
import com.clooy.binge.navigation.BingeNavHost

@Composable
internal fun BingeApp(
    modifier: Modifier = Modifier,
    appState: BingeAppState,
) {
    BingeBackground(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        BingeNavHost(
            appState = appState,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
        )
    }
}
