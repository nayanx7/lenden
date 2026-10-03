package com.example.data.repository

import com.example.data.local.CardDao
import com.example.data.local.NotificationDao
import com.example.data.local.TransactionDao
import com.example.data.model.CardEntity
import com.example.data.model.Contact
import com.example.data.model.NotificationEntity
import com.example.data.model.TransactionEntity
import com.example.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class LendenRepository(
    private val transactionDao: TransactionDao,
    private val cardDao: CardDao,
    private val notificationDao: NotificationDao
) {
    val allTransactions: Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()
    val allCards: Flow<List<CardEntity>> = cardDao.getAllCards()
    val allNotifications: Flow<List<NotificationEntity>> = notificationDao.getAllNotifications()

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile = _userProfile.asStateFlow()

    val contacts: List<Contact> = listOf(
        Contact("c1", "Alex Morgan", "@alexm", "+880 1712 998877", "AM"),
        Contact("c2", "Sarah Chen", "@sarahc", "+880 1819 223344", "SC"),
        Contact("c3", "David Miller", "@davidm", "+880 1911 556677", "DM"),
        Contact("c4", "Elena Rostova", "@elenar", "+880 1612 884422", "ER"),
        Contact("c5", "Marcus Vance", "@marcusv", "+880 1715 339900", "MV")
    )

    suspend fun initializeSeedDataIfNeeded() {
        val existingTxns = allTransactions.first()
        if (existingTxns.isEmpty()) {
            val now = System.currentTimeMillis()
            val day = 86_400_000L

            val seedTransactions = listOf(
                TransactionEntity(
                    title = "Transfer to Alex Morgan",
                    subtitle = "Monthly Rent Share • Instant",
                    amount = 286.00,
                    isIncome = false,
                    timestamp = now - 1000 * 60 * 18,
                    category = "Transfer",
                    status = "Completed",
                    referenceCode = "TXN-8839210",
                    recipientName = "Alex Morgan",
                    fee = 0.00
                ),
                TransactionEntity(
                    title = "Artisan Coffee Roasters",
                    subtitle = "Espresso & Croissant • Terminal 3",
                    amount = 4.50,
                    isIncome = false,
                    timestamp = now - 1000 * 60 * 120,
                    category = "Food",
                    status = "Completed",
                    referenceCode = "TXN-8839184",
                    recipientName = "Coffee Shop"
                ),
                TransactionEntity(
                    title = "Apple Store Online",
                    subtitle = "Studio Display Cable & MagSafe",
                    amount = 84.20,
                    isIncome = false,
                    timestamp = now - day * 1,
                    category = "Shopping",
                    status = "Completed",
                    referenceCode = "TXN-8838902",
                    recipientName = "Apple Online"
                ),
                TransactionEntity(
                    title = "TechCorp Global Inc.",
                    subtitle = "Bi-weekly Payroll • Direct Deposit",
                    amount = 4250.00,
                    isIncome = true,
                    timestamp = now - day * 2,
                    category = "Salary",
                    status = "Completed",
                    referenceCode = "TXN-8837119",
                    recipientName = "TechCorp Global"
                ),
                TransactionEntity(
                    title = "Spotify Premium Duo",
                    subtitle = "Recurring Monthly Subscription",
                    amount = 11.99,
                    isIncome = false,
                    timestamp = now - day * 3,
                    category = "Subscription",
                    status = "Completed",
                    referenceCode = "TXN-8836014",
                    recipientName = "Spotify AB"
                ),
                TransactionEntity(
                    title = "Amazon Web Services",
                    subtitle = "Cloud Computing Hosting",
                    amount = 48.70,
                    isIncome = false,
                    timestamp = now - day * 4,
                    category = "Utility",
                    status = "Completed",
                    referenceCode = "TXN-8835100",
                    recipientName = "AWS EMEA"
                )
            )

            seedTransactions.forEach { transactionDao.insertTransaction(it) }
        }

        val existingCards = allCards.first()
        if (existingCards.isEmpty()) {
            val seedCards = listOf(
                CardEntity(
                    cardName = "LENDEN Black",
                    cardType = "Mastercard World Elite",
                    cardNumberMasked = "•••• •••• •••• 4912",
                    cardNumberFull = "5412 8892 3410 4912",
                    expDate = "09/29",
                    cvv = "834",
                    isFrozen = false,
                    spendingLimit = 15000.0,
                    currentSpent = 3280.50,
                    currency = "USD",
                    styleType = "BLACK"
                ),
                CardEntity(
                    cardName = "LENDEN Virtual",
                    cardType = "Visa Signature Disposable",
                    cardNumberMasked = "•••• •••• •••• 7183",
                    cardNumberFull = "4102 9941 7183 2291",
                    expDate = "03/28",
                    cvv = "419",
                    isFrozen = false,
                    spendingLimit = 2500.0,
                    currentSpent = 480.00,
                    currency = "USD",
                    styleType = "VIRTUAL"
                ),
                CardEntity(
                    cardName = "LENDEN Travel",
                    cardType = "Multi-Currency Platinum",
                    cardNumberMasked = "•••• •••• •••• 3054",
                    cardNumberFull = "4532 1084 3054 6610",
                    expDate = "11/30",
                    cvv = "902",
                    isFrozen = false,
                    spendingLimit = 10000.0,
                    currentSpent = 1120.00,
                    currency = "EUR",
                    styleType = "TRAVEL"
                )
            )

            seedCards.forEach { cardDao.insertCard(it) }
        }

        val existingNotifications = allNotifications.first()
        if (existingNotifications.isEmpty()) {
            val now = System.currentTimeMillis()
            val seedNotifications = listOf(
                NotificationEntity(
                    title = "Transfer completed",
                    body = "$286.00 sent successfully to Alex Morgan with zero fees.",
                    timestamp = now - 1000 * 60 * 18,
                    type = "TRANSACTION",
                    isRead = false
                ),
                NotificationEntity(
                    title = "Your card was used",
                    body = "$84.20 approved at Apple Store Online using LENDEN Black.",
                    timestamp = now - 86_400_000L,
                    type = "TRANSACTION",
                    isRead = false
                ),
                NotificationEntity(
                    title = "Payment received",
                    body = "Salary deposit of $4,250.00 from TechCorp is available.",
                    timestamp = now - 86_400_000L * 2,
                    type = "TRANSACTION",
                    isRead = true
                ),
                NotificationEntity(
                    title = "Security alert",
                    body = "Biometric Face ID authentication verified on Pixel 9 Pro.",
                    timestamp = now - 86_400_000L * 3,
                    type = "SECURITY",
                    isRead = true
                )
            )

            seedNotifications.forEach { notificationDao.insertNotification(it) }
        }
    }

    suspend fun toggleCardFreeze(card: CardEntity) {
        cardDao.setCardFrozen(card.id, !card.isFrozen)
    }

    suspend fun sendMoney(
        recipientName: String,
        amount: Double,
        note: String
    ): TransactionEntity {
        val now = System.currentTimeMillis()
        val txn = TransactionEntity(
            title = "Transfer to $recipientName",
            subtitle = if (note.isNotBlank()) note else "Direct Instant Transfer",
            amount = amount,
            isIncome = false,
            timestamp = now,
            category = "Transfer",
            status = "Completed",
            referenceCode = "TXN-${(1000000..9999999).random()}",
            recipientName = recipientName,
            fee = 0.00
        )
        val id = transactionDao.insertTransaction(txn)

        notificationDao.insertNotification(
            NotificationEntity(
                title = "Transfer completed",
                body = "$${String.format("%.2f", amount)} successfully sent to $recipientName.",
                timestamp = now,
                type = "TRANSACTION",
                isRead = false
            )
        )

        // update balance
        val currentProfile = _userProfile.value
        _userProfile.value = currentProfile.copy(
            totalBalance = maxOf(0.0, currentProfile.totalBalance - amount),
            availableBalance = maxOf(0.0, currentProfile.availableBalance - amount)
        )

        return txn.copy(id = id)
    }

    suspend fun markAllNotificationsRead() {
        notificationDao.markAllAsRead()
    }

    fun updateSecuritySettings(biometric: Boolean, twoFactor: Boolean) {
        _userProfile.value = _userProfile.value.copy(
            biometricEnabled = biometric,
            twoFactorEnabled = twoFactor
        )
    }
}
