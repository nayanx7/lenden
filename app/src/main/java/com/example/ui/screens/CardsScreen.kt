package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CardEntity
import com.example.ui.components.LendenCardView
import com.example.ui.theme.LendenEmerald
import com.example.ui.theme.LendenMint

@Composable
fun CardsScreen(
    cards: List<CardEntity>,
    isDetailsRevealed: Boolean,
    onToggleRevealDetails: () -> Unit,
    onToggleFreeze: (CardEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedCardIndex by remember { mutableIntStateOf(0) }

    val activeCard = if (cards.isNotEmpty() && selectedCardIndex < cards.size) {
        cards[selectedCardIndex]
    } else {
        cards.firstOrNull()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Title Header
        item {
            Column(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = "LENDEN Metallic & Virtual",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Card Management",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Card Selection Tabs
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cards.forEachIndexed { index, card ->
                    val isSelected = index == selectedCardIndex
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedCardIndex = index }
                            .testTag("tab_card_${card.styleType.lowercase()}"),
                        shape = RoundedCornerShape(14.dp),
                        color = if (isSelected) LendenEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) LendenEmerald else MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = card.cardName.replace("LENDEN ", ""),
                                color = if (isSelected) LendenEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Active 3D Floating Physical Card View
        item {
            if (activeCard != null) {
                val tiltY by animateFloatAsState(
                    targetValue = if (isDetailsRevealed) -4f else 0f,
                    animationSpec = tween(400),
                    label = "tilt"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            rotationY = tiltY
                            cameraDistance = 12 * density
                        }
                ) {
                    LendenCardView(
                        card = activeCard,
                        isDetailsRevealed = isDetailsRevealed,
                        onToggleReveal = onToggleRevealDetails
                    )
                }
            }
        }

        // Quick Card Actions (Freeze Card, Details, Add to Wallet)
        item {
            if (activeCard != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Freeze/Unfreeze Button
                    Button(
                        onClick = { onToggleFreeze(activeCard) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_freeze_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (activeCard.isFrozen) Color(0xFFEF4444) else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (activeCard.isFrozen) Color.White else MaterialTheme.colorScheme.onSurface
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (activeCard.isFrozen) Color(0xFFEF4444) else MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Icon(
                            imageVector = if (activeCard.isFrozen) Icons.Default.LockOpen else Icons.Default.Lock,
                            contentDescription = "Freeze",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (activeCard.isFrozen) "Unfreeze" else "Freeze Card",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // Reveal Details
                    Button(
                        onClick = onToggleRevealDetails,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_reveal_details"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Icon(
                            imageVector = if (isDetailsRevealed) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Details",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isDetailsRevealed) "Hide Info" else "Card Info",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // Add to Google Wallet
                    Button(
                        onClick = {
                            Toast.makeText(context, "Card added to Google Wallet securely.", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_add_google_wallet"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LendenEmerald.copy(alpha = 0.15f),
                            contentColor = LendenEmerald
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald.copy(alpha = 0.4f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBalanceWallet,
                            contentDescription = "Wallet",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Add Wallet",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Spending Limit & Progress
        item {
            if (activeCard != null) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Monthly Spending Limit",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$${String.format("%.2f", activeCard.currentSpent)} / $${String.format("%.0f", activeCard.spendingLimit)}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val progress = (activeCard.currentSpent / activeCard.spendingLimit).toFloat().coerceIn(0f, 1f)
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = LendenEmerald,
                            trackColor = MaterialTheme.colorScheme.surface
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Remaining limit for October: $${String.format("%.2f", activeCard.spendingLimit - activeCard.currentSpent)}",
                            color = LendenEmerald,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Card Features & Settings
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Card Security Controls",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    CardControlToggleRow(
                        title = "Online Transactions",
                        subtitle = "Enable for e-commerce and app purchases",
                        initialValue = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CardControlToggleRow(
                        title = "Contactless NFC Payments",
                        subtitle = "Tap to pay at physical POS terminals",
                        initialValue = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CardControlToggleRow(
                        title = "International Roaming",
                        subtitle = "Zero foreign transaction fees globally",
                        initialValue = true
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CardControlToggleRow(
    title: String,
    subtitle: String,
    initialValue: Boolean
) {
    var isChecked by remember { androidx.compose.runtime.mutableStateOf(initialValue) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = LendenEmerald,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}
