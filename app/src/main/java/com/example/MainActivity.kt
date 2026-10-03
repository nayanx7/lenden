package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.LendenDatabase
import com.example.data.repository.LendenRepository
import com.example.ui.components.LendenBottomNavBar
import com.example.ui.components.LendenTopBar
import com.example.ui.screens.ActivityScreen
import com.example.ui.screens.CardsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.KycFlowScreen
import com.example.ui.screens.NotificationsBottomSheet
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ReceiveMoneyBottomSheet
import com.example.ui.screens.SecurityScreen
import com.example.ui.screens.SendMoneyBottomSheet
import com.example.ui.screens.SupportChatBottomSheet
import com.example.ui.screens.TransactionDetailSheet
import com.example.ui.screens.TransferSuccessDialog
import com.example.ui.screens.WalletScreen
import com.example.ui.showcase.CinematicShowcaseView
import com.example.ui.theme.LendenTheme
import com.example.ui.viewmodel.LendenViewModel
import com.example.ui.viewmodel.LendenViewModelFactory
import com.example.ui.viewmodel.NavTab

class MainActivity : ComponentActivity() {

    private val viewModel: LendenViewModel by viewModels {
        val db = LendenDatabase.getDatabase(applicationContext)
        val repository = LendenRepository(
            transactionDao = db.transactionDao(),
            cardDao = db.cardDao(),
            notificationDao = db.notificationDao()
        )
        LendenViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LendenTheme(darkTheme = uiState.isDarkMode) {
                if (uiState.isCinematicMode) {
                    // Fullscreen 3D Floating Phone Cinematic Showcase Commercial View
                    CinematicShowcaseView(
                        currentTimeSec = uiState.cinematicTimeSeconds,
                        isPlaying = uiState.isCinematicPlaying,
                        playbackSpeed = uiState.cinematicPlaybackSpeed,
                        onTogglePlayPause = { viewModel.toggleCinematicPlayPause() },
                        onSeek = { viewModel.setCinematicTime(it) },
                        onSpeedChange = { viewModel.setCinematicSpeed(it) },
                        onJumpToPart = { viewModel.jumpToCinematicPart(it) },
                        onExitShowcase = { viewModel.exitCinematicMode() }
                    )
                } else if (uiState.showKycFlow) {
                    BackHandler { viewModel.closeKycFlow() }
                    KycFlowScreen(
                        currentStep = uiState.kycStep,
                        onStepChange = { viewModel.setKycStep(it) },
                        onFinish = { viewModel.closeKycFlow() },
                        onClose = { viewModel.closeKycFlow() }
                    )
                } else if (uiState.showSecurityScreen) {
                    BackHandler { viewModel.closeSecurityScreen() }
                    SecurityScreen(
                        userProfile = uiState.userProfile,
                        onToggleBiometric = { viewModel.toggleBiometric(it) },
                        onToggleTwoFactor = { viewModel.toggleTwoFactor(it) },
                        onBack = { viewModel.closeSecurityScreen() }
                    )
                } else {
                    // Main Authenticated Fintech Application Experience
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.background,
                        topBar = {
                            LendenTopBar(
                                unreadNotificationCount = uiState.notifications.count { !it.isRead },
                                onOpenNotifications = { viewModel.openNotifications() },
                                onOpenCinematicShowcase = { viewModel.enterCinematicMode() },
                                onOpenSupportChat = { viewModel.openSupportChat() },
                                onOpenSecurity = { viewModel.openSecurityScreen() }
                            )
                        },
                        bottomBar = {
                            LendenBottomNavBar(
                                activeTab = uiState.activeNavTab,
                                onTabSelected = { viewModel.setNavTab(it) }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            AnimatedContent(
                                targetState = uiState.activeNavTab,
                                transitionSpec = { fadeIn() togetherWith fadeOut() },
                                label = "nav_tabs"
                            ) { tab ->
                                when (tab) {
                                    NavTab.HOME -> HomeScreen(
                                        balance = uiState.userProfile.totalBalance,
                                        transactions = uiState.transactions,
                                        cards = uiState.cards,
                                        onSendClick = { viewModel.openSendMoney() },
                                        onReceiveClick = { viewModel.openReceiveMoney() },
                                        onTransactionClick = { viewModel.openTransactionDetails(it) },
                                        onViewAllTransactions = { viewModel.setNavTab(NavTab.ACTIVITY) },
                                        onOpenShowcase = { viewModel.enterCinematicMode() },
                                        onManageCards = { viewModel.setNavTab(NavTab.CARDS) }
                                    )
                                    NavTab.WALLET -> WalletScreen(
                                        totalBalance = uiState.userProfile.totalBalance,
                                        availableBalance = uiState.userProfile.availableBalance,
                                        pendingBalance = uiState.userProfile.pendingBalance,
                                        onSendClick = { viewModel.openSendMoney() },
                                        onReceiveClick = { viewModel.openReceiveMoney() }
                                    )
                                    NavTab.CARDS -> CardsScreen(
                                        cards = uiState.cards,
                                        isDetailsRevealed = uiState.isCardDetailsRevealed,
                                        onToggleRevealDetails = { viewModel.toggleCardDetailsReveal() },
                                        onToggleFreeze = { viewModel.toggleCardFreeze(it) }
                                    )
                                    NavTab.ACTIVITY -> ActivityScreen(
                                        transactions = uiState.transactions,
                                        onTransactionClick = { viewModel.openTransactionDetails(it) }
                                    )
                                    NavTab.PROFILE -> ProfileScreen(
                                        userProfile = uiState.userProfile,
                                        isDarkMode = uiState.isDarkMode,
                                        onToggleDarkMode = { viewModel.toggleDarkMode() },
                                        onOpenSecurity = { viewModel.openSecurityScreen() },
                                        onOpenKycFlow = { viewModel.openKycFlow() },
                                        onOpenSupportChat = { viewModel.openSupportChat() },
                                        onOpenShowcase = { viewModel.enterCinematicMode() }
                                    )
                                }
                            }
                        }
                    }

                    // Sheets & Modals
                    if (uiState.showNotificationsSheet) {
                        NotificationsBottomSheet(
                            notifications = uiState.notifications,
                            onMarkAllRead = { viewModel.markAllNotificationsRead() },
                            onDismiss = { viewModel.closeNotifications() }
                        )
                    }

                    if (uiState.showSendMoneySheet) {
                        SendMoneyBottomSheet(
                            contacts = uiState.contacts,
                            onDismiss = { viewModel.closeSendMoney() },
                            onConfirmSend = { recipient, amount, note ->
                                viewModel.executeSendMoney(recipient, amount, note)
                            }
                        )
                    }

                    if (uiState.showReceiveMoneySheet) {
                        ReceiveMoneyBottomSheet(
                            onDismiss = { viewModel.closeReceiveMoney() }
                        )
                    }

                    if (uiState.selectedTransaction != null) {
                        TransactionDetailSheet(
                            transaction = uiState.selectedTransaction,
                            onDismiss = { viewModel.closeTransactionDetails() }
                        )
                    }

                    if (uiState.showSupportChat) {
                        SupportChatBottomSheet(
                            messages = uiState.chatMessages,
                            onSendMessage = { viewModel.sendChatMessage(it) },
                            onDismiss = { viewModel.closeSupportChat() }
                        )
                    }

                    if (uiState.showSuccessReceipt && uiState.lastCompletedTxn != null) {
                        TransferSuccessDialog(
                            transaction = uiState.lastCompletedTxn,
                            onDismiss = { viewModel.dismissSuccessReceipt() }
                        )
                    }
                }
            }
        }
    }
}
