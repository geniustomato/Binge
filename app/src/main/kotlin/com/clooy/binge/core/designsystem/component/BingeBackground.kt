package com.clooy.binge.core.designsystem.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BingeBackground(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = color,
    ) {
        content()
    }
}
