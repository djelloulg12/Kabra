package com.example.data.model

import androidx.annotation.DrawableRes

/**
 * Model representing a member of the Nigerien delegation
 * (Passport and phone numbers have been omitted for privacy and security)
 */
data class Participant(
    val id: Int,
    val fullName: String,
    val gender: String, // "M" or "F"
    val specialty: String // "تركيب ألواح الطاقة الشمسية" or "كهرباء المعمار"
) {
    val isMale: Boolean get() = gender.equals("M", ignoreCase = true)
    val genderLabel: String get() = if (isMale) "ذكر" else "أنثى"

    fun localizedGender(isArabic: Boolean): String =
        if (isArabic) genderLabel else (if (isMale) "Masculin (M)" else "Féminin (F)")

    fun localizedSpecialty(isArabic: Boolean): String =
        if (isArabic) {
            specialty
        } else {
            if (specialty.contains("طاقة")) "Installation Panneaux Solaires Photovoltaïques" else "Électricité du Bâtiment"
        }
}

/**
 * Model for Conversational AI Practice Partner
 */
data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: MessageSender,
    val text: String,
    val translation: String? = null,
    val phoneticPronunciation: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val isGenerating: Boolean = false
)

enum class MessageSender {
    USER,
    AI_PARTNER
}

/**
 * Cultural Landmark Model for the visual gallery
 */
data class CulturalLandmark(
    val id: String,
    val name: String,
    val nameFr: String = "",
    val location: String,
    val locationFr: String = "",
    val summary: String,
    val summaryFr: String = "",
    val detailedHistory: String,
    val detailedHistoryFr: String = "",
    val architecturalFeatures: List<String>,
    val architecturalFeaturesFr: List<String> = emptyList(),
    val tipsForVisitors: String,
    val tipsForVisitorsFr: String = "",
    @param:DrawableRes val drawableRes: Int
) {
    fun localizedName(isArabic: Boolean): String = if (isArabic || nameFr.isBlank()) name else nameFr
    fun localizedLocation(isArabic: Boolean): String = if (isArabic || locationFr.isBlank()) location else locationFr
    fun localizedSummary(isArabic: Boolean): String = if (isArabic || summaryFr.isBlank()) summary else summaryFr
    fun localizedHistory(isArabic: Boolean): String = if (isArabic || detailedHistoryFr.isBlank()) detailedHistory else detailedHistoryFr
    fun localizedFeatures(isArabic: Boolean): List<String> = if (isArabic || architecturalFeaturesFr.isEmpty()) architecturalFeatures else architecturalFeaturesFr
    fun localizedTips(isArabic: Boolean): String = if (isArabic || tipsForVisitorsFr.isBlank()) tipsForVisitors else tipsForVisitorsFr
}
