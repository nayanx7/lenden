package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val subtitle: String,
    val amount: Double,
    val isIncome: Boolean,
    val timestamp: Long,
    val category: String, // Transfer, Salary, Food, Shopping, Subscription, Investment, Utility
    val status: String = "Completed", // Completed, Pending, Failed
    val referenceCode: String = "",
    val recipientName: String = "",
    val fee: Double = 0.0,
    val paymentMethod: String = "LENDEN Black •• 4912"
)

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cardName: String, // "LENDEN Black", "LENDEN Virtual", "LENDEN Travel"
    val cardType: String, // "Mastercard World Elite", "Visa Signature", "LENDEN Platinum"
    val cardNumberMasked: String, // "•••• •••• •••• 4912"
    val cardNumberFull: String, // "5412 8892 3410 4912"
    val expDate: String, // "09/29"
    val cvv: String, // "834"
    val isFrozen: Boolean = false,
    val spendingLimit: Double = 15000.0,
    val currentSpent: Double = 3280.50,
    val currency: String = "USD",
    val styleType: String = "BLACK" // BLACK, VIRTUAL, TRAVEL
)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val body: String,
    val timestamp: Long,
    val isRead: Boolean = false,
    val type: String = "TRANSACTION" // TRANSACTION, SECURITY, SYSTEM, ALERT
)

data class Contact(
    val id: String,
    val name: String,
    val handle: String,
    val phone: String,
    val avatarInitials: String,
    val isFrequent: Boolean = true
)

data class UserProfile(
    val fullName: String = "Alex Morgan",
    val email: String = "alex.morgan@lenden.com",
    val phone: String = "+880 1712 345678",
    val kycStatus: String = "VERIFIED",
    val tier: String = "LENDEN Private Club",
    val totalBalance: Double = 8420.50,
    val availableBalance: Double = 8134.50,
    val pendingBalance: Double = 286.00,
    val financialHealthScore: Int = 94,
    val biometricEnabled: Boolean = true,
    val twoFactorEnabled: Boolean = true,
    val e2eEncryptionEnabled: Boolean = true
)
