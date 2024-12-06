package com.picpay.desafio.android.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val colorPrimary = Color(0xFF2B2C2F)
private val colorPrimaryDark = Color(0xFF1D1E20)
private val colorAccent = Color(0xFF11C76F)
private val colorPrimaryLight = Color(0xFFACB1BD)
private val colorDetail = Color(0x80FFFFFF)

val LightColorScheme = lightColorScheme(
    primary = colorPrimary,
    onPrimary = Color.White,
    primaryContainer = colorPrimaryLight,
    onPrimaryContainer = Color.Black,
    secondary = colorAccent,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = colorPrimaryDark,
    surface = colorPrimaryLight,
    onSurface = colorPrimaryDark,
    surfaceVariant = colorDetail,
    onSurfaceVariant = colorPrimaryDark
)

val DarkColorScheme = darkColorScheme(
    primary = colorPrimary,
    onPrimary = Color.White,
    primaryContainer = colorPrimaryDark,
    onPrimaryContainer = colorPrimaryLight,
    secondary = colorAccent,
    onSecondary = Color.Black,
    background = colorPrimaryDark,
    onBackground = colorPrimaryLight,
    surface = colorPrimaryDark,
    onSurface = colorPrimaryLight,
    surfaceVariant = colorDetail,
    onSurfaceVariant = colorPrimaryLight
)