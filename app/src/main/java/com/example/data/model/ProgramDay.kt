package com.example.data.model

import androidx.annotation.DrawableRes

/**
 * Model representing a schedule paragraph / activity within a day
 */
data class ScheduleParagraph(
    val id: String,
    val time: String,
    val title: String,
    val description: String = "",
    val titleFr: String = "",
    val descriptionFr: String = "",
    val iconName: String = "schedule"
) {
    fun localizedTitle(isArabic: Boolean): String = if (isArabic || titleFr.isBlank()) title else titleFr
    fun localizedDescription(isArabic: Boolean): String = if (isArabic || descriptionFr.isBlank()) description else descriptionFr
}

/**
 * Model representing a specific day in the cultural and touristic program
 */
data class ProgramDay(
    val id: String,
    val dayNumber: Int,
    val dateString: String,
    val dateStringFr: String = "",
    val gregorianDate: String,
    val title: String,
    val titleFr: String = "",
    val shortSubtitle: String,
    val shortSubtitleFr: String = "",
    val location: String,
    val locationFr: String = "",
    val timeRange: String,
    val timeRangeFr: String = "",
    val paragraphs: List<ScheduleParagraph>,
    val justification: String,
    val justificationFr: String = "",
    val culturalHighlights: List<String>,
    val culturalHighlightsFr: List<String> = emptyList(),
    @param:DrawableRes val illustrationRes: Int,
    val category: EventCategory = EventCategory.EXCURSION
) {
    fun localizedTitle(isArabic: Boolean): String = if (isArabic || titleFr.isBlank()) title else titleFr
    fun localizedDate(isArabic: Boolean): String = if (isArabic || dateStringFr.isBlank()) dateString else dateStringFr
    fun localizedSubtitle(isArabic: Boolean): String = if (isArabic || shortSubtitleFr.isBlank()) shortSubtitle else shortSubtitleFr
    fun localizedLocation(isArabic: Boolean): String = if (isArabic || locationFr.isBlank()) location else locationFr
    fun localizedTimeRange(isArabic: Boolean): String = if (isArabic || timeRangeFr.isBlank()) timeRange else timeRangeFr
    fun localizedJustification(isArabic: Boolean): String = if (isArabic || justificationFr.isBlank()) justification else justificationFr
    fun localizedHighlights(isArabic: Boolean): List<String> = if (isArabic || culturalHighlightsFr.isEmpty()) culturalHighlights else culturalHighlightsFr
}

enum class EventCategory(val labelAr: String, val labelFr: String, val colorHex: Long) {
    WELCOME("استقبال وترحيب", "Accueil & Bienvenue", 0xFF00796B),
    EXCURSION("رحلة سياحية وثقافية", "Excursion Culturelle", 0xFFD84315),
    FOLKLORE("سهرة فنية وتراثية", "Soirée Folklorique", 0xFF6A1B9A),
    CINEMA_HISTORY("تاريخ وذاكرة وطنية", "Histoire & Mémoire", 0xFFC62828),
    RELAXATION("استجمام وراحة", "Détente & Thermalisme", 0xFF00838F),
    CEREMONY("حفل رسمي وختام", "Cérémonie Officielle", 0xFF283593),
    RELIGIOUS("شعائر وروحانيات", "Spiritualité & Culte", 0xFF2E7D32);

    val label: String get() = labelAr
    fun localizedLabel(isArabic: Boolean): String = if (isArabic) labelAr else labelFr
}
