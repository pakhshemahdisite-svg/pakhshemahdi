package com.pakhshmahdi.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.runtime.CompositionLocalProvider

private val PakhshMahdiColors = lightColorScheme(
    primary = PMPrimary,
    onPrimary = PMWhite,
    secondary = PMSecondary,
    onSecondary = PMWhite,
    background = PMBackground,
    onBackground = PMText,
    surface = PMWhite,
    onSurface = PMText,
    outline = PMBorder
)

@Composable
fun PakhshMahdiTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = PakhshMahdiColors) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            content()
        }
    }
}
