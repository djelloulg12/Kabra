package com.example.data.model

import androidx.annotation.DrawableRes

data class MapLandmark(
    val id: String,
    val name: String,
    val nameFr: String = "",
    val subtitle: String,
    val subtitleFr: String = "",
    val category: String,
    val categoryFr: String = "",
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val descriptionFr: String = "",
    val associatedDayId: String? = null,
    @param:DrawableRes val drawableRes: Int,
    val markerColorHex: Long = 0xFF00695C
) {
    fun localizedName(isArabic: Boolean): String = if (isArabic || nameFr.isBlank()) name else nameFr
    fun localizedSubtitle(isArabic: Boolean): String = if (isArabic || subtitleFr.isBlank()) subtitle else subtitleFr
    fun localizedCategory(isArabic: Boolean): String = if (isArabic || categoryFr.isBlank()) category else categoryFr
    fun localizedDescription(isArabic: Boolean): String = if (isArabic || descriptionFr.isBlank()) description else descriptionFr
}
