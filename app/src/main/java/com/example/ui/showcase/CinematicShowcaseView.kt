package com.example.ui.showcase

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LendenEmerald
import com.example.ui.theme.LendenMint
import kotlin.math.cos
import kotlin.math.sin

data class ShowcasePartInfo(
    val partNumber: Int,
    val title: String,
    val timeRange: String,
    val startSec: Float,
    val endSec: Float
)

val SHOWCASE_PARTS = listOf(
    ShowcasePartInfo(1, "Opening", "0–2.5s", 0f, 2.5f),
    ShowcasePartInfo(2, "Splash / Brand", "2.5–4s", 2.5f, 4f),
    ShowcasePartInfo(3, "Onboarding", "4–6s", 4f, 6f),
    ShowcasePartInfo(4, "Sign Up", "6–8.5s", 6f, 8.5f),
    ShowcasePartInfo(5, "OTP Verification", "8.5–10s", 8.5f, 10f),
    ShowcasePartInfo(6, "KYC Intro (1/4)", "10–12s", 10f, 12f),
    ShowcasePartInfo(7, "KYC Personal (2/4)", "12–14s", 12f, 14f),
    ShowcasePartInfo(8, "KYC Document (3/4)", "14–16s", 14f, 16f),
    ShowcasePartInfo(9, "KYC Selfie (4/4)", "16–18s", 16f, 18f),
    ShowcasePartInfo(10, "Account Ready", "18–19.5s", 18f, 19.5f),
    ShowcasePartInfo(11, "Home Dashboard", "19.5–22s", 19.5f, 22f),
    ShowcasePartInfo(12, "Wallet", "22–24s", 22f, 24f),
    ShowcasePartInfo(13, "Cards 3D Tilt", "24–26s", 24f, 26f),
    ShowcasePartInfo(14, "Send Money", "26–29s", 26f, 29f),
    ShowcasePartInfo(15, "Transfer Complete", "29–31s", 29f, 31f),
    ShowcasePartInfo(16, "Request Money", "31–33s", 31f, 33f),
    ShowcasePartInfo(17, "Activity History", "33–35s", 33f, 35f),
    ShowcasePartInfo(18, "Transaction Details", "35–36.5s", 35f, 36.5f),
    ShowcasePartInfo(19, "Notifications", "36.5–38s", 36.5f, 38f),
    ShowcasePartInfo(20, "Profile & KYC", "38–40s", 38f, 40f),
    ShowcasePartInfo(21, "Security & 2FA", "40–42s", 40f, 42f),
    ShowcasePartInfo(22, "Settings", "42–43.5s", 42f, 43.5f),
    ShowcasePartInfo(23, "Final Hero", "43.5–45s", 43.5f, 45f)
)

@Composable
fun CinematicShowcaseView(
    currentTimeSec: Float,
    isPlaying: Boolean,
    playbackSpeed: Float,
    onTogglePlayPause: () -> Unit,
    onSeek: (Float) -> Unit,
    onSpeedChange: (Float) -> Unit,
    onJumpToPart: (Int) -> Unit,
    onExitShowcase: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentPart = remember(currentTimeSec) {
        SHOWCASE_PARTS.firstOrNull { currentTimeSec in it.startSec..it.endSec }
            ?: SHOWCASE_PARTS.last()
    }

    // Camera 3D Dynamics based on timeline:
    // Cinematic subtle camera orbit, push-in, macro zoom, and pull-back
    val progressFraction = currentTimeSec / 45f
    val cameraAngle = (sin(currentTimeSec.toDouble() * 0.35) * 5.5).toFloat()
    val cameraTiltX = when {
        currentTimeSec < 2.5f -> (1f - (currentTimeSec / 2.5f)) * 14f // phone rising into frame
        currentTimeSec in 24f..26f -> (sin((currentTimeSec.toDouble() - 24.0) * 3.14159) * 8.0).toFloat() // 3D card macro tilt
        currentTimeSec >= 43f -> 0f // Final hero level
        else -> (cos(currentTimeSec.toDouble() * 0.4) * 3.0).toFloat()
    }
    val cameraScale = when {
        currentTimeSec < 2.5f -> 0.88f + (currentTimeSec / 2.5f) * 0.12f
        currentTimeSec in 14f..18f -> 1.05f // macro scan zoom
        currentTimeSec in 35f..37f -> 1.06f // transaction detail macro
        currentTimeSec >= 43.5f -> 0.92f // Final hero pull-out
        else -> (1.0 + sin(currentTimeSec.toDouble() * 0.2) * 0.03).toFloat()
    }
    val phoneTranslationY = when {
        currentTimeSec < 2.5f -> (1f - (currentTimeSec / 2.5f)) * 80f // rises up
        else -> (sin(currentTimeSec.toDouble() * 0.5) * 6.0).toFloat() // subtle ambient floating hover
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEBEFF5)) // Clean warm-white / light-gray studio background per spec
    ) {
        val availableHeight = maxHeight
        val availableWidth = maxWidth

        // Studio lighting ambient radiance & reflections
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Soft key light at top-left
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.8f),
                        Color(0xFFE5EBF2),
                        Color(0xFFDCE2EB)
                    ),
                    center = Offset(size.width * 0.3f, size.height * 0.25f),
                    radius = size.width * 0.9f
                ),
                radius = size.width * 0.9f,
                center = Offset(size.width * 0.3f, size.height * 0.25f)
            )

            // Soft studio ambient floor shadow beneath the phone
            val shadowWidth = size.width * 0.65f * cameraScale
            val shadowHeight = 28.dp.toPx() * cameraScale
            val shadowCenter = Offset(size.width * 0.5f, size.height * 0.68f)

            drawOval(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x381E293B),
                        Color(0x181E293B),
                        Color.Transparent
                    ),
                    center = shadowCenter,
                    radius = shadowWidth * 0.6f
                ),
                topLeft = Offset(shadowCenter.x - shadowWidth / 2, shadowCenter.y - shadowHeight / 2),
                size = androidx.compose.ui.geometry.Size(shadowWidth, shadowHeight)
            )
        }

        // Top Cinematic Bar with Current Scene Tag & Exit button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.Black.copy(alpha = 0.75f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(LendenEmerald)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "PART ${currentPart.partNumber}: ${currentPart.title.uppercase()}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            // Exit to Interactive App
            Button(
                onClick = onExitShowcase,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black.copy(alpha = 0.8f),
                    contentColor = LendenEmerald
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald.copy(alpha = 0.4f)),
                modifier = Modifier.testTag("btn_exit_showcase")
            ) {
                Icon(
                    imageVector = Icons.Default.TouchApp,
                    contentDescription = "Interactive App",
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Interactive App", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Central Photorealistic Floating Flagship Smartphone
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 180.dp),
            contentAlignment = Alignment.Center
        ) {
            val phoneWidth = 275.dp
            val phoneHeight = 580.dp

            Box(
                modifier = Modifier
                    .size(width = phoneWidth, height = phoneHeight)
                    .graphicsLayer {
                        rotationY = cameraAngle.toFloat()
                        rotationX = cameraTiltX.toFloat()
                        scaleX = cameraScale.toFloat()
                        scaleY = cameraScale.toFloat()
                        translationY = phoneTranslationY.toFloat()
                        cameraDistance = 16 * density
                    }
                    // Outer smartphone titanium border frame
                    .shadow(
                        elevation = 28.dp,
                        shape = RoundedCornerShape(44.dp),
                        spotColor = Color(0x770F172A),
                        ambientColor = Color(0x440F172A)
                    )
                    .clip(RoundedCornerShape(44.dp))
                    .background(Color(0xFF0F172A))
                    .border(
                        width = 4.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF94A3B8),
                                Color(0xFF334155),
                                Color(0xFF1E293B),
                                Color(0xFF64748B)
                            )
                        ),
                        shape = RoundedCornerShape(44.dp)
                    )
                    .padding(3.dp) // bezel thickness
            ) {
                // Inner Screen Bezels & Glass Display
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(0xFF090D14)) // flagship deep screen surface
                ) {
                    // Render the Active Scene UI inside the phone display!
                    ShowcaseSceneDisplay(
                        partNumber = currentPart.partNumber,
                        timeSec = currentTimeSec
                    )

                    // Speaker ear slit at top
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 7.dp)
                            .size(width = 48.dp, height = 4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFF1E293B))
                    )

                    // Camera punch hole
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 14.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF070A0F))
                            .border(0.5.dp, Color(0xFF223046), CircleShape)
                    )

                    // Studio glass specular reflection sheen running across the display
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.08f),
                                        Color.White.copy(alpha = 0.02f),
                                        Color.Transparent
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(400f, 800f)
                                )
                            )
                    )
                }
            }
        }

        // Bottom Controls Overlay (Scrubber, Play/Pause, Speed, Parts jumping)
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding(),
            color = Color(0xF20A0E17),
            tonalElevation = 16.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1F293D))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                // Timeline Scrubber & Timestamps
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${String.format("%.1f", currentTimeSec)}s",
                        color = LendenEmerald,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                    Text(
                        text = "45.0s (Cinematic Commercial)",
                        color = Color(0xFF94A3B8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Slider(
                    value = currentTimeSec,
                    onValueChange = onSeek,
                    valueRange = 0f..45f,
                    colors = SliderDefaults.colors(
                        thumbColor = LendenEmerald,
                        activeTrackColor = LendenEmerald,
                        inactiveTrackColor = Color(0xFF1E293B)
                    ),
                    modifier = Modifier.fillMaxWidth().testTag("showcase_timeline_slider")
                )

                // Play/Pause, Speeds & Quick Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onTogglePlayPause,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(LendenEmerald)
                                .testTag("btn_showcase_play_pause")
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.Black,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Speeds: 1x, 1.5x, 2x
                        listOf(1.0f, 1.5f, 2.0f).forEach { speed ->
                            val isSel = playbackSpeed == speed
                            Surface(
                                onClick = { onSpeedChange(speed) },
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSel) LendenEmerald.copy(alpha = 0.2f) else Color.Transparent,
                                modifier = Modifier.padding(horizontal = 2.dp)
                            ) {
                                Text(
                                    text = "${speed}x",
                                    color = if (isSel) LendenEmerald else Color(0xFF94A3B8),
                                    fontSize = 11.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    // Part Jump Selector Chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.widthIn(max = 180.dp)
                    ) {
                        itemsIndexed(SHOWCASE_PARTS) { index, part ->
                            val isCurrent = currentPart.partNumber == part.partNumber
                            Surface(
                                onClick = { onJumpToPart(part.partNumber) },
                                shape = RoundedCornerShape(8.dp),
                                color = if (isCurrent) LendenEmerald else Color(0xFF1E293B),
                                modifier = Modifier.testTag("part_jump_${part.partNumber}")
                            ) {
                                Text(
                                    text = "P${part.partNumber}",
                                    color = if (isCurrent) Color.Black else Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
