package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.CardEntity
import com.example.data.model.Contact
import com.example.data.model.NotificationEntity
import com.example.data.model.TransactionEntity
import com.example.data.model.UserProfile
import com.example.data.repository.LendenRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ChatMessage(
    val id: String,
    val sender: String, // "USER" or "LENDEN Concierge"
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class LendenUiState(
    val transactions: List<TransactionEntity> = emptyList(),
    val cards: List<CardEntity> = emptyList(),
    val notifications: List<NotificationEntity> = emptyList(),
    val userProfile: UserProfile = UserProfile(),
    val contacts: List<Contact> = emptyList(),
    val activeNavTab: NavTab = NavTab.HOME,
    val isCinematicMode: Boolean = false,
    val cinematicTimeSeconds: Float = 0f,
    val isCinematicPlaying: Boolean = false,
    val cinematicPlaybackSpeed: Float = 1.0f,
    val selectedTransaction: TransactionEntity? = null,
    val showSendMoneySheet: Boolean = false,
    val showReceiveMoneySheet: Boolean = false,
    val showKycFlow: Boolean = false,
    val kycStep: Int = 1,
    val isKycScanning: Boolean = false,
    val showSupportChat: Boolean = false,
    val chatMessages: List<ChatMessage> = listOf(
        ChatMessage("m1", "LENDEN Concierge", "Good day Alex. How may LENDEN Private Banking assist you today?"),
        ChatMessage("m2", "LENDEN Concierge", "Your account is protected by 256-bit encryption and biometric hardware security.")
    ),
    val showSecurityScreen: Boolean = false,
    val isDarkMode: Boolean = true,
    val isCardDetailsRevealed: Boolean = false,
    val lastCompletedTxn: TransactionEntity? = null,
    val showSuccessReceipt: Boolean = false,
    val showNotificationsSheet: Boolean = false
)

enum class NavTab(val label: String) {
    HOME("Home"),
    WALLET("Wallet"),
    CARDS("Cards"),
    ACTIVITY("Activity"),
    PROFILE("Profile")
}

class LendenViewModel(
    private val repository: LendenRepository
) : ViewModel() {

    private val _activeNavTab = MutableStateFlow(NavTab.HOME)
    private val _isCinematicMode = MutableStateFlow(false)
    private val _cinematicTimeSeconds = MutableStateFlow(0f)
    private val _isCinematicPlaying = MutableStateFlow(false)
    private val _cinematicPlaybackSpeed = MutableStateFlow(1.0f)
    private val _selectedTransaction = MutableStateFlow<TransactionEntity?>(null)
    private val _showSendMoneySheet = MutableStateFlow(false)
    private val _showReceiveMoneySheet = MutableStateFlow(false)
    private val _showKycFlow = MutableStateFlow(false)
    private val _kycStep = MutableStateFlow(1)
    private val _isKycScanning = MutableStateFlow(false)
    private val _showSupportChat = MutableStateFlow(false)
    private val _chatMessages = MutableStateFlow(
        listOf(
            ChatMessage("m1", "LENDEN Concierge", "Good day Alex. How may LENDEN Private Banking assist you today?"),
            ChatMessage("m2", "LENDEN Concierge", "Your account is protected by 256-bit encryption and biometric hardware security.")
        )
    )
    private val _showSecurityScreen = MutableStateFlow(false)
    private val _isDarkMode = MutableStateFlow(true)
    private val _isCardDetailsRevealed = MutableStateFlow(false)
    private val _lastCompletedTxn = MutableStateFlow<TransactionEntity?>(null)
    private val _showSuccessReceipt = MutableStateFlow(false)
    private val _showNotificationsSheet = MutableStateFlow(false)

    private var cinematicJob: Job? = null

    init {
        viewModelScope.launch {
            repository.initializeSeedDataIfNeeded()
        }
    }

    val uiState: StateFlow<LendenUiState> = combine(
        repository.allTransactions,
        repository.allCards,
        repository.allNotifications,
        repository.userProfile,
        _activeNavTab,
        _isCinematicMode,
        _cinematicTimeSeconds,
        _isCinematicPlaying,
        _cinematicPlaybackSpeed,
        _selectedTransaction,
        _showSendMoneySheet,
        _showReceiveMoneySheet,
        _showKycFlow,
        _kycStep,
        _isKycScanning,
        _showSupportChat,
        _chatMessages,
        _showSecurityScreen,
        _isDarkMode,
        _isCardDetailsRevealed,
        _lastCompletedTxn,
        _showSuccessReceipt,
        _showNotificationsSheet
    ) { args ->
        @Suppress("UNCHECKED_CAST")
        LendenUiState(
            transactions = args[0] as List<TransactionEntity>,
            cards = args[1] as List<CardEntity>,
            notifications = args[2] as List<NotificationEntity>,
            userProfile = args[3] as UserProfile,
            contacts = repository.contacts,
            activeNavTab = args[4] as NavTab,
            isCinematicMode = args[5] as Boolean,
            cinematicTimeSeconds = args[6] as Float,
            isCinematicPlaying = args[7] as Boolean,
            cinematicPlaybackSpeed = args[8] as Float,
            selectedTransaction = args[9] as TransactionEntity?,
            showSendMoneySheet = args[10] as Boolean,
            showReceiveMoneySheet = args[11] as Boolean,
            showKycFlow = args[12] as Boolean,
            kycStep = args[13] as Int,
            isKycScanning = args[14] as Boolean,
            showSupportChat = args[15] as Boolean,
            chatMessages = args[16] as List<ChatMessage>,
            showSecurityScreen = args[17] as Boolean,
            isDarkMode = args[18] as Boolean,
            isCardDetailsRevealed = args[19] as Boolean,
            lastCompletedTxn = args[20] as TransactionEntity?,
            showSuccessReceipt = args[21] as Boolean,
            showNotificationsSheet = args[22] as Boolean
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LendenUiState(contacts = repository.contacts)
    )

    fun setNavTab(tab: NavTab) {
        _activeNavTab.value = tab
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    fun openTransactionDetails(txn: TransactionEntity) {
        _selectedTransaction.value = txn
    }

    fun closeTransactionDetails() {
        _selectedTransaction.value = null
    }

    fun openSendMoney(prefillRecipient: String = "Alex Morgan", prefillAmount: Double = 286.0) {
        _showSendMoneySheet.value = true
    }

    fun closeSendMoney() {
        _showSendMoneySheet.value = false
    }

    fun openReceiveMoney() {
        _showReceiveMoneySheet.value = true
    }

    fun closeReceiveMoney() {
        _showReceiveMoneySheet.value = false
    }

    fun openKycFlow() {
        _showKycFlow.value = true
        _kycStep.value = 1
    }

    fun setKycStep(step: Int) {
        _kycStep.value = step.coerceIn(1, 4)
    }

    fun nextKycStep() {
        if (_kycStep.value < 4) {
            _kycStep.value += 1
        } else {
            _showKycFlow.value = false
        }
    }

    fun closeKycFlow() {
        _showKycFlow.value = false
    }

    fun toggleCardFreeze(card: CardEntity) {
        viewModelScope.launch {
            repository.toggleCardFreeze(card)
        }
    }

    fun toggleCardDetailsReveal() {
        _isCardDetailsRevealed.value = !_isCardDetailsRevealed.value
    }

    fun executeSendMoney(recipient: String, amount: Double, note: String) {
        viewModelScope.launch {
            val txn = repository.sendMoney(recipient, amount, note)
            _lastCompletedTxn.value = txn
            _showSendMoneySheet.value = false
            _showSuccessReceipt.value = true
        }
    }

    fun dismissSuccessReceipt() {
        _showSuccessReceipt.value = false
    }

    fun openSupportChat() {
        _showSupportChat.value = true
    }

    fun closeSupportChat() {
        _showSupportChat.value = false
    }

    fun sendChatMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = ChatMessage("user_${System.currentTimeMillis()}", "USER", text)
        _chatMessages.value = _chatMessages.value + userMsg

        viewModelScope.launch {
            delay(900)
            val replyText = when {
                text.contains("card", ignoreCase = true) -> "Your LENDEN Black card is active and protected. You can toggle freeze or adjust spending limits anytime."
                text.contains("transfer", ignoreCase = true) || text.contains("send", ignoreCase = true) -> "Transfers within LENDEN are processed in sub-seconds with zero network fees."
                text.contains("limit", ignoreCase = true) -> "Your daily verified limit is $50,000.00. Multi-signature approvals apply for transfers over $10,000."
                text.contains("security", ignoreCase = true) || text.contains("encrypt", ignoreCase = true) -> "LENDEN employs end-to-end 256-bit AES cryptographic protocols with biometric hardware enclaves."
                else -> "Thank you for contacting LENDEN Priority Assistance. An authorized wealth advisor has been alerted."
            }
            val replyMsg = ChatMessage("concierge_${System.currentTimeMillis()}", "LENDEN Concierge", replyText)
            _chatMessages.value = _chatMessages.value + replyMsg
        }
    }

    fun openSecurityScreen() {
        _showSecurityScreen.value = true
    }

    fun closeSecurityScreen() {
        _showSecurityScreen.value = false
    }

    fun openNotifications() {
        _showNotificationsSheet.value = true
    }

    fun closeNotifications() {
        _showNotificationsSheet.value = false
    }

    fun toggleBiometric(enabled: Boolean) {
        repository.updateSecuritySettings(enabled, uiState.value.userProfile.twoFactorEnabled)
    }

    fun toggleTwoFactor(enabled: Boolean) {
        repository.updateSecuritySettings(uiState.value.userProfile.biometricEnabled, enabled)
    }

    fun markAllNotificationsRead() {
        viewModelScope.launch {
            repository.markAllNotificationsRead()
        }
    }

    // --- Cinematic Showcase Mode Controls ---

    fun enterCinematicMode(startPlaying: Boolean = true) {
        _isCinematicMode.value = true
        if (startPlaying) {
            playCinematic()
        }
    }

    fun exitCinematicMode() {
        pauseCinematic()
        _isCinematicMode.value = false
    }

    fun toggleCinematicPlayPause() {
        if (_isCinematicPlaying.value) {
            pauseCinematic()
        } else {
            playCinematic()
        }
    }

    fun playCinematic() {
        _isCinematicPlaying.value = true
        cinematicJob?.cancel()
        cinematicJob = viewModelScope.launch {
            val stepMs = 50L
            while (_isCinematicPlaying.value) {
                delay(stepMs)
                val current = _cinematicTimeSeconds.value
                val speed = _cinematicPlaybackSpeed.value
                val next = current + (stepMs / 1000f) * speed
                if (next >= 45.0f) {
                    _cinematicTimeSeconds.value = 0f // loop or hold
                } else {
                    _cinematicTimeSeconds.value = next
                }
            }
        }
    }

    fun pauseCinematic() {
        _isCinematicPlaying.value = false
        cinematicJob?.cancel()
    }

    fun setCinematicTime(timeSec: Float) {
        _cinematicTimeSeconds.value = timeSec.coerceIn(0f, 45f)
    }

    fun setCinematicSpeed(speed: Float) {
        _cinematicPlaybackSpeed.value = speed
    }

    fun jumpToCinematicPart(partNumber: Int) {
        val targetSeconds = when (partNumber) {
            1 -> 1.0f   // Part 1: Opening (0-2.5s)
            2 -> 3.0f   // Part 2: Splash (2.5-4s)
            3 -> 5.0f   // Part 3: Onboarding (4-6s)
            4 -> 7.0f   // Part 4: Sign Up (6-8.5s)
            5 -> 9.0f   // Part 5: OTP Verification (8.5-10s)
            6 -> 11.0f  // Part 6: KYC Intro (10-12s)
            7 -> 13.0f  // Part 7: KYC Personal Info (12-14s)
            8 -> 15.0f  // Part 8: KYC Document Scan (14-16s)
            9 -> 17.0f  // Part 9: KYC Facial positioning (16-18s)
            10 -> 18.8f // Part 10: Account Ready (18-19.5s)
            11 -> 20.5f // Part 11: Home Dashboard (19.5-22s)
            12 -> 23.0f // Part 12: Wallet (22-24s)
            13 -> 25.0f // Part 13: Cards (24-26s)
            14 -> 27.5f // Part 14: Send Money (26-29s)
            15 -> 30.0f // Part 15: Transfer Confirmation (29-31s)
            16 -> 32.0f // Part 16: Request Money (31-33s)
            17 -> 34.0f // Part 17: Transaction History (33-35s)
            18 -> 35.8f // Part 18: Transaction Details (35-36.5s)
            19 -> 37.2f // Part 19: Notifications (36.5-38s)
            20 -> 39.0f // Part 20: Profile (38-40s)
            21 -> 41.0f // Part 21: Security (40-42s)
            22 -> 42.8f // Part 22: Settings & Portfolio (42-43.5s)
            23 -> 44.2f // Part 23: Final Hero (43.5-45s)
            else -> 0f
        }
        setCinematicTime(targetSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        cinematicJob?.cancel()
    }
}

class LendenViewModelFactory(
    private val repository: LendenRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LendenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LendenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
