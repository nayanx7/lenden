package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CardEntity
import com.example.ui.theme.LendenEmerald
import com.example.ui.theme.LendenMint

@Composable
fun LendenCardView(
    card: CardEntity,
    isDetailsRevealed: Boolean = false,
    onToggleReveal: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val gradientBrush = when (card.styleType) {
        "VIRTUAL" -> Brush.linearGradient(
            colors = listOf(
                Color(0xFF034133),
                Color(0xFF007A57),
                Color(0xFF00C087)
            )
        )
        "TRAVEL" -> Brush.linearGradient(
            colors = listOf(
                Color(0xFF0F1E36),
                Color(0xFF1E3A5F),
                Color(0xFF274B7A)
            )
        )
        else -> Brush.linearGradient( // BLACK
            colors = listOf(
                Color(0xFF1F242E),
                Color(0xFF10141C),
                Color(0xFF0A0D13)
            )
        )
    }

    val cardAccentColor = when (card.styleType) {
        "VIRTUAL" -> Color.White
        "TRAVEL" -> Color(0xFF64B5F6)
        else -> LendenEmerald
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.586f) // Standard Credit Card ISO/IEC 7810 ID-1 ratio (85.60 × 53.98 mm)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = Color.Black.copy(alpha = 0.5f),
                ambientColor = Color.Black.copy(alpha = 0.4f)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(gradientBrush)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.35f),
                        Color.White.copy(alpha = 0.05f),
                        cardAccentColor.copy(alpha = 0.25f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .testTag("card_view_${card.styleType.lowercase()}")
    ) {
        // Holographic subtle watermark pattern
        Box(
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            cardAccentColor.copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Card Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header: LENDEN Brand & Contactless / Frozen badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "LENDEN",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = when (card.styleType) {
                            "VIRTUAL" -> "VIRTUAL"
                            "TRAVEL" -> "TRAVEL"
                            else -> "BLACK"
                        },
                        color = cardAccentColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (card.isFrozen) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFEF4444).copy(alpha = 0.25f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEF4444).copy(alpha = 0.6f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Frozen",
                                    tint = Color(0xFFFF6B6B),
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "FROZEN",
                                    color = Color(0xFFFF6B6B),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.Nfc,
                            contentDescription = "Contactless",
                            tint = Color.White.copy(alpha = 0.75f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Middle: EMV Metallic Chip Graphic
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Realistic Metallic EMV Smart Chip
                Box(
                    modifier = Modifier
                        .size(width = 42.dp, height = 30.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFE2C974),
                                    Color(0xFFF9E8A2),
                                    Color(0xFFC7A740)
                                )
                            )
                        )
                        .border(
                            0.7.dp,
                            Color(0xFF8A6C1B),
                            RoundedCornerShape(6.dp)
                        )
                ) {
                    // Chip circuitry lines
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                            .border(0.5.dp, Color(0x66000000), RoundedCornerShape(3.dp))
                    )
                }

                // Eye Toggle to reveal details if callback provided
                if (onToggleReveal != null) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onToggleReveal() }
                            .background(Color.White.copy(alpha = 0.1f))
                            .padding(6.dp)
                    ) {
                        Icon(
                            imageVector = if (isDetailsRevealed) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle details",
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Card Number
            Text(
                text = if (isDetailsRevealed) card.cardNumberFull else card.cardNumberMasked,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp
            )

            // Footer: Cardholder Name, Expiry, CVV & Network
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "CARDHOLDER",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "ALEX MORGAN",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }

                Column {
                    Text(
                        text = "EXPIRES",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = card.expDate,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                if (isDetailsRevealed) {
                    Column {
                        Text(
                            text = "CVV",
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = card.cvv,
                            color = cardAccentColor,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                // Payment Network Brand (Mastercard / Visa styling)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEB001B).copy(alpha = 0.85f))
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .offset(x = (-8).dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF79E1B).copy(alpha = 0.85f))
                    )
                }
            }
        }
    }
}
