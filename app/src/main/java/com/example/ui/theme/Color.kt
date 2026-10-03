package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// LENDEN Signature Brand Colors
val LendenEmerald = Color(0xFF00C087)
val LendenMint = Color(0xFF00E599)
val LendenDarkTeal = Color(0xFF02382C)
val LendenDeepForest = Color(0xFF05201A)

// Neutral Dark Palette (Flagship Studio Dark)
val LendenOnyx = Color(0xFF080B11)
val LendenDarkSurface = Color(0xFF111722)
val LendenDarkSurfaceElevated = Color(0xFF182232)
val LendenDarkBorder = Color(0xFF222F43)
val LendenDarkBorderSubtle = Color(0xFF1B2536)

// Neutral Light Palette (Warm Minimal Studio Light)
val LendenLightBg = Color(0xFFF7F9FC)
val LendenLightSurface = Color(0xFFFFFFFF)
val LendenLightSurfaceElevated = Color(0xFFF0F4F8)
val LendenLightBorder = Color(0xFFE2E8F0)
val LendenLightBorderSubtle = Color(0xFFEDF2F7)

// Text Colors
val LendenTextWhite = Color(0xFFFFFFFF)
val LendenTextMutedDark = Color(0xFF8B9CB2)
val LendenTextPrimaryLight = Color(0xFF0F172A)
val LendenTextMutedLight = Color(0xFF64748B)

// Accent Colors
val LendenBlue = Color(0xFF2E6BFF)
val LendenAmber = Color(0xFFF59E0B)
val LendenRose = Color(0xFFF43F5E)
val LendenPurple = Color(0xFF8B5CF6)

// Gradients
val LendenEmeraldGradient = Brush.linearGradient(
    colors = listOf(LendenEmerald, LendenMint)
)

val LendenBlackCardGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF1B2330), Color(0xFF0C1017), Color(0xFF141A24))
)

val LendenVirtualCardGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF02382C), Color(0xFF00C087))
)

val LendenTravelCardGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF1E293B), Color(0xFF334155), Color(0xFF0F172A))
)

val StudioLightingGradient = Brush.radialGradient(
    colors = listOf(
        Color(0x3300C087),
        Color(0x0A00C087),
        Color.Transparent
    )
)
