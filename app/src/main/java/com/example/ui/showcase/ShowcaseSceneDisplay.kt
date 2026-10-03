package com.example.ui.showcase

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.LendenEmerald
import com.example.ui.theme.LendenMint
import kotlin.math.sin

@Composable
fun ShowcaseSceneDisplay(
    partNumber: Int,
    timeSec: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 26.dp)
    ) {
        when (partNumber) {
            1 -> ScenePart1Opening()
            2 -> ScenePart2Splash()
            3 -> ScenePart3Onboarding()
            4 -> ScenePart4SignUp()
            5 -> ScenePart5Otp(timeSec)
            6 -> ScenePart6KycIntro()
            7 -> ScenePart7KycPersonal()
            8 -> ScenePart8KycDoc(timeSec)
            9 -> ScenePart9KycSelfie(timeSec)
            10 -> ScenePart10AccountReady()
            11 -> ScenePart11HomeDashboard()
            12 -> ScenePart12Wallet()
            13 -> ScenePart13Cards(timeSec)
            14 -> ScenePart14SendMoney()
            15 -> ScenePart15TransferConfirm()
            16 -> ScenePart16RequestMoney()
            17 -> ScenePart17ActivityHistory()
            18 -> ScenePart18TransactionDetails()
            19 -> ScenePart19Notifications()
            20 -> ScenePart20Profile()
            21 -> ScenePart21Security()
            22 -> ScenePart22Settings()
            23 -> ScenePart23FinalHero()
            else -> ScenePart11HomeDashboard()
        }
    }
}

// -------------------------------------------------------------
// PART 1 to PART 23 DETAILED IMPLEMENTATIONS
// -------------------------------------------------------------

@Composable
fun ScenePart1Opening() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF131A26)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "L",
                color = LendenEmerald,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "LENDEN",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp
        )
    }
}

@Composable
fun ScenePart2Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF16202E), Color(0xFF0F151F))
                    )
                )
                .border(1.dp, LendenEmerald.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "L",
                color = LendenEmerald,
                fontSize = 38.sp,
                fontWeight = FontWeight.Black
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "LENDEN",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Money, Made Simple.",
            color = LendenEmerald,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
fun ScenePart3Onboarding() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "LENDEN", color = LendenEmerald, fontSize = 16.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "Your money.\nOne simple place.",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Effortless global banking, multi-currency wallets, instant transfers and 256-bit institutional security.",
                color = Color(0xFF94A3B8),
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }

        Column(modifier = Modifier.padding(bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = LendenEmerald,
                modifier = Modifier.fillMaxWidth().height(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Get Started", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF131A26),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF243247)),
                modifier = Modifier.fillMaxWidth().height(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Sign In", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun ScenePart4SignUp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Create your account", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Enter your details to register with LENDEN", color = Color(0xFF94A3B8), fontSize = 10.sp)

            Spacer(modifier = Modifier.height(14.dp))

            ShowcaseFieldItem(label = "Full Name", value = "Alex Morgan")
            Spacer(modifier = Modifier.height(8.dp))
            ShowcaseFieldItem(label = "Email Address", value = "alex.morgan@lenden.com")
            Spacer(modifier = Modifier.height(8.dp))
            ShowcaseFieldItem(label = "Phone Number", value = "+880 1712 345678")
            Spacer(modifier = Modifier.height(8.dp))
            ShowcaseFieldItem(label = "Password", value = "••••••••••••")
        }

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = LendenEmerald,
            modifier = Modifier.fillMaxWidth().height(42.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Create Account", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart5Otp(timeSec: Float) {
    val filledCount = when {
        timeSec < 8.8f -> 2
        timeSec < 9.2f -> 4
        timeSec < 9.6f -> 6
        else -> 6
    }
    val isVerified = timeSec >= 9.6f
    val digits = listOf("8", "9", "2", "1", "0", "4")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Verify your phone", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "+880 1712 ******", color = Color(0xFF94A3B8), fontSize = 12.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            digits.forEachIndexed { idx, digit ->
                val hasDigit = idx < filledCount
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF131A26))
                        .border(
                            1.dp,
                            if (hasDigit) LendenEmerald else Color(0xFF243247),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (hasDigit) digit else "",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        if (isVerified) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = LendenEmerald.copy(alpha = 0.2f),
                border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald)
            ) {
                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Verified", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ScenePart6KycIntro() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Identity Verification", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "1 / 4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = "Verify your identity", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Complete identity verification to unlock all LENDEN features and high-volume limits.",
                color = Color(0xFF94A3B8),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            ShowcaseBullet("Government Institutional Standards")
            ShowcaseBullet("Hardware Cryptographic Enclave")
            ShowcaseBullet("Zero-Knowledge Identity Vault")
        }

        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Continue", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart7KycPersonal() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Personal Information", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "2 / 4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(14.dp))
            ShowcaseFieldItem("Full Legal Name", "Alex Morgan")
            Spacer(modifier = Modifier.height(6.dp))
            ShowcaseFieldItem("Date of Birth", "1996-08-14")
            Spacer(modifier = Modifier.height(6.dp))
            ShowcaseFieldItem("Nationality", "Global Institutional")
            Spacer(modifier = Modifier.height(6.dp))
            ShowcaseFieldItem("Residential Address", "742 Financial Way, Suite 400")
        }
        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Next Step", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart8KycDoc(timeSec: Float) {
    val laserProgress = ((timeSec * 2) % 1f)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Document Verification", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "3 / 4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Verify your identity document", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Surface(shape = RoundedCornerShape(8.dp), color = LendenEmerald.copy(alpha = 0.2f), border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald)) {
                    Text(text = "National ID", color = LendenEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
                Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF131A26)) {
                    Text(text = "Passport", color = Color(0xFF94A3B8), fontSize = 10.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
                Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF131A26)) {
                    Text(text = "Driver's", color = Color(0xFF94A3B8), fontSize = 10.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Scanning box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF111722))
                    .border(1.dp, LendenEmerald.copy(alpha = 0.6f), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Badge, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(44.dp))
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val y = size.height * laserProgress
                    drawLine(color = Color(0xFF00E599), start = Offset(8f, y), end = Offset(size.width - 8f, y), strokeWidth = 3f)
                }
            }
        }
        Text(text = "Document verified successfully", color = LendenEmerald, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ScenePart9KycSelfie(timeSec: Float) {
    val isComplete = timeSec >= 17.2f
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Facial Position", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "4 / 4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "Position face inside frame", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF111722))
                    .border(2.dp, if (isComplete) LendenEmerald else Color(0xFF3B82F6), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null, tint = if (isComplete) LendenEmerald else Color(0xFF64748B), modifier = Modifier.size(64.dp))
                if (isComplete) {
                    Box(modifier = Modifier.fillMaxSize().background(LendenEmerald.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
        Text(
            text = if (isComplete) "Verification complete" else "Aligning biometric points...",
            color = if (isComplete) LendenEmerald else Color(0xFF94A3B8),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ScenePart10AccountReady() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(LendenEmerald.copy(alpha = 0.2f))
                .border(2.dp, LendenEmerald, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(36.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Your account is ready.", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Welcome to LENDEN.", color = LendenEmerald, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(24.dp))
        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Continue", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart11HomeDashboard() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = "Good morning", color = Color(0xFF94A3B8), fontSize = 10.sp)
                Text(text = "Alex Morgan", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            Surface(shape = RoundedCornerShape(8.dp), color = LendenEmerald.copy(alpha = 0.15f)) {
                Text(text = "Verified", color = LendenEmerald, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
        }

        // Balance Card
        Surface(shape = RoundedCornerShape(14.dp), color = Color(0xFF121824), border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF223046))) {
            Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
                Text(text = "TOTAL BALANCE", color = Color(0xFF94A3B8), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = "$8,420", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text(text = ".50", color = Color(0xFF94A3B8), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "USD", color = LendenEmerald, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Quick Actions
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ShowcaseMiniAction(Icons.Default.ArrowUpward, "Send", LendenEmerald)
            ShowcaseMiniAction(Icons.Default.ArrowDownward, "Receive", Color(0xFF3B82F6))
            ShowcaseMiniAction(Icons.Default.Payment, "Pay", Color(0xFFF59E0B))
            ShowcaseMiniAction(Icons.Default.Add, "Add Money", Color(0xFF10B981))
        }

        // Mini Card
        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFF1B2330), modifier = Modifier.fillMaxWidth().height(65.dp)) {
            Row(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "LENDEN BLACK", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text(text = "•••• 4912", color = Color(0xFF94A3B8), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                }
                Text(text = "$3,280 spent", color = LendenEmerald, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        // Mini Txn
        Surface(shape = RoundedCornerShape(10.dp), color = Color(0xFF121824)) {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(26.dp).clip(CircleShape).background(Color(0xFF1E293B)), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.Work, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(14.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Salary Deposit", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
                Text(text = "+$4,250.00", color = LendenEmerald, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart12Wallet() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Wallet Balances", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Surface(shape = RoundedCornerShape(14.dp), color = Color(0xFF121824), border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF223046))) {
            Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
                Text(text = "Total Balance", color = Color(0xFF94A3B8), fontSize = 10.sp)
                Text(text = "$8,420.50", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = "Available", color = Color(0xFF94A3B8), fontSize = 10.sp)
                        Text(text = "$8,134.50", color = LendenEmerald, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Pending", color = Color(0xFF94A3B8), fontSize = 10.sp)
                        Text(text = "$286.00", color = Color(0xFFF59E0B), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Account Details Row
        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFF121824)) {
            Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "IBAN", color = Color(0xFF94A3B8), fontSize = 9.sp)
                    Text(text = "GB82 LEND 0092 1084 92", color = Color.White, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                }
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(16.dp))
            }
        }

        // Currency Rows
        ShowcaseMiniCurrency("USD Account", "$8,420.50")
        ShowcaseMiniCurrency("EUR Vault", "€2,150.00")
        ShowcaseMiniCurrency("BDT Direct", "৳925,000")
    }
}

@Composable
fun ScenePart13Cards(timeSec: Float) {
    val tilt = sin((timeSec - 24f) * 3.14) * 8f

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "LENDEN Cards", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))

            // Main floating 3D tilt card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF1E2633), Color(0xFF0F141E), Color(0xFF161C26))
                        )
                    )
                    .border(1.dp, LendenEmerald.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "LENDEN BLACK", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "ACTIVE", color = LendenEmerald, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                    Text(text = "•••• •••• •••• 4912", color = Color.White, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "ALEX MORGAN", color = Color(0xFF94A3B8), fontSize = 10.sp)
                        Text(text = "09/29", color = Color.White, fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Card Action Buttons matching spec: Freeze Card, Details, Add to Wallet
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF1E293B), modifier = Modifier.weight(1f).height(36.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Freeze Card", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF1E293B), modifier = Modifier.weight(1f).height(36.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Details", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Surface(shape = RoundedCornerShape(8.dp), color = LendenEmerald.copy(alpha = 0.2f), modifier = Modifier.weight(1f).height(36.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Add to Wallet", color = LendenEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Text(text = "Instant zero-fee card issuance globally", color = Color(0xFF94A3B8), fontSize = 10.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ScenePart14SendMoney() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "Send Money", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            // Recipient Alex Morgan
            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFF121824)) {
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(34.dp).clip(CircleShape).background(LendenEmerald), contentAlignment = Alignment.Center) {
                        Text(text = "AM", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(text = "Alex Morgan", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "+880 1712 998877", color = Color(0xFF94A3B8), fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Amount $286.00
            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFF121824), border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald)) {
                Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
                    Text(text = "Amount", color = Color(0xFF94A3B8), fontSize = 10.sp)
                    Text(text = "$286.00", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            ShowcaseFieldItem("Note", "Monthly Rent Share")
        }

        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Continue", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart15TransferConfirm() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "Confirm Transfer", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Surface(shape = RoundedCornerShape(14.dp), color = Color(0xFF121824), border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF223046))) {
                Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Send", color = Color(0xFF94A3B8), fontSize = 11.sp)
                        Text(text = "$286.00", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Recipient", color = Color(0xFF94A3B8), fontSize = 11.sp)
                        Text(text = "Alex Morgan", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Fee", color = Color(0xFF94A3B8), fontSize = 11.sp)
                        Text(text = "$0.00", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "$286.00", color = LendenEmerald, fontSize = 16.sp, fontWeight = FontWeight.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transfer Complete badge
            Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald.copy(alpha = 0.2f), border = androidx.compose.foundation.BorderStroke(1.dp, LendenEmerald)) {
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = LendenEmerald, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Transfer Complete", color = LendenEmerald, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Text(text = "Zero-fee institutional settlement", color = Color(0xFF94A3B8), fontSize = 10.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ScenePart16RequestMoney() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Request Money", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(14.dp))

            // Mini QR
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.QrCode, contentDescription = null, tint = Color.Black, modifier = Modifier.size(100.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "https://pay.lenden.me/req/883921", color = LendenEmerald, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }

        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(42.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Share Payment Link", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart17ActivityHistory() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Activity", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        ShowcaseMiniTxnRow("Coffee Shop", "-$4.50", false, Icons.Default.Coffee)
        ShowcaseMiniTxnRow("Online Purchase", "-$84.20", false, Icons.Default.ShoppingBag)
        ShowcaseMiniTxnRow("Salary Deposit", "+$4,250.00", true, Icons.Default.Work)
        ShowcaseMiniTxnRow("Transfer to Alex", "-$286.00", false, Icons.Default.ArrowUpward)
        ShowcaseMiniTxnRow("Subscription", "-$11.99", false, Icons.Default.Subscriptions)
    }
}

@Composable
fun ScenePart18TransactionDetails() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "Transaction Details", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))

            Surface(shape = RoundedCornerShape(14.dp), color = Color(0xFF121824), border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF223046))) {
                Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "-$286.00", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Status: Completed", color = LendenEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    ShowcaseKeyValue("Recipient", "Alex Morgan")
                    ShowcaseKeyValue("Date", "Oct 03, 2026")
                    ShowcaseKeyValue("Time", "10:42 AM")
                    ShowcaseKeyValue("Reference", "TXN-8839210")
                    ShowcaseKeyValue("Payment", "LENDEN Black")
                }
            }
        }

        Surface(shape = RoundedCornerShape(12.dp), color = LendenEmerald, modifier = Modifier.fillMaxWidth().height(40.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Download Receipt", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ScenePart19Notifications() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Notification Center", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        ShowcaseMiniNotif("Transfer completed", "$286.00 sent to Alex Morgan", LendenEmerald)
        ShowcaseMiniNotif("Your card was used", "$84.20 approved at Apple Store", Color(0xFF3B82F6))
        ShowcaseMiniNotif("Payment received", "Salary deposit of $4,250.00", LendenEmerald)
        ShowcaseMiniNotif("Security alert", "Face ID login on Pixel 9 Pro", Color(0xFFF59E0B))
    }
}

@Composable
fun ScenePart20Profile() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(54.dp).clip(CircleShape).background(Color(0xFF1E293B)), contentAlignment = Alignment.Center) {
            Text(text = "AM", color = LendenEmerald, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Text(text = "Alex Morgan", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text(text = "alex.morgan@lenden.com", color = Color(0xFF94A3B8), fontSize = 10.sp)

        Surface(shape = RoundedCornerShape(10.dp), color = LendenEmerald.copy(alpha = 0.15f)) {
            Text(text = "Verified Account Level 3", color = LendenEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
        }

        Spacer(modifier = Modifier.height(4.dp))
        ShowcaseMenuRow("Personal Information")
        ShowcaseMenuRow("Payment Methods")
        ShowcaseMenuRow("Security & 2FA")
        ShowcaseMenuRow("Preferences")
    }
}

@Composable
fun ScenePart21Security() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Security & Biometrics", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        ShowcaseToggleRow("Face ID / Biometric", true)
        ShowcaseToggleRow("Two-Factor Auth (2FA)", true)
        ShowcaseToggleRow("Transaction PIN", true)
        ShowcaseToggleRow("Trusted Devices", true)
        ShowcaseToggleRow("AES-256 Encryption", true)
    }
}

@Composable
fun ScenePart22Settings() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Settings", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        ShowcaseMenuRow("Appearance (Dark Theme)")
        ShowcaseMenuRow("Push Notifications")
        ShowcaseMenuRow("Base Currency (USD)")
        ShowcaseMenuRow("Privacy & Data Rights")
        ShowcaseMenuRow("About LENDEN v2.4")
    }
}

@Composable
fun ScenePart23FinalHero() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF16202E), Color(0xFF0F151F))
                    )
                )
                .border(1.dp, LendenEmerald, RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "L", color = LendenEmerald, fontSize = 42.sp, fontWeight = FontWeight.Black)
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "LENDEN", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, letterSpacing = 5.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Money, Made Simple.", color = LendenEmerald, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
    }
}

// -------------------------------------------------------------
// SHOWCASE HELPER COMPONENTS
// -------------------------------------------------------------

@Composable
fun ShowcaseFieldItem(label: String, value: String) {
    Surface(shape = RoundedCornerShape(10.dp), color = Color(0xFF131A26), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text(text = label, color = Color(0xFF64748B), fontSize = 9.sp)
            Text(text = value, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ShowcaseBullet(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(LendenEmerald))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.White, fontSize = 11.sp)
    }
}

@Composable
fun ShowcaseMiniAction(icon: ImageVector, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(38.dp).clip(CircleShape).background(Color(0xFF16202E)), contentAlignment = Alignment.Center) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = Color(0xFF94A3B8), fontSize = 9.sp)
    }
}

@Composable
fun ShowcaseMiniCurrency(name: String, amount: String) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF121824)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = name, color = Color(0xFF94A3B8), fontSize = 10.sp)
            Text(text = amount, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ShowcaseMiniTxnRow(title: String, amount: String, isIncome: Boolean, icon: ImageVector) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF121824)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(22.dp).clip(CircleShape).background(Color(0xFF1E293B)), contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = if (isIncome) LendenEmerald else Color(0xFF94A3B8), modifier = Modifier.size(12.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, color = Color.White, fontSize = 10.sp)
            }
            Text(text = amount, color = if (isIncome) LendenEmerald else Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ShowcaseKeyValue(k: String, v: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = k, color = Color(0xFF94A3B8), fontSize = 10.sp)
        Text(text = v, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ShowcaseMiniNotif(title: String, body: String, tint: Color) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF121824)) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(tint))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = title, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text(text = body, color = Color(0xFF94A3B8), fontSize = 9.sp)
            }
        }
    }
}

@Composable
fun ShowcaseMenuRow(title: String) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF121824)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 7.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, color = Color.White, fontSize = 10.sp)
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun ShowcaseToggleRow(title: String, checked: Boolean) {
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF121824)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, color = Color.White, fontSize = 10.sp)
            Box(modifier = Modifier.size(width = 24.dp, height = 12.dp).clip(RoundedCornerShape(6.dp)).background(LendenEmerald))
        }
    }
}
