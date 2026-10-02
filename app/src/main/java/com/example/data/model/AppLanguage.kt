package com.example.data.model

enum class AppLanguage(val code: String, val label: String, val flag: String) {
    ARABIC("ar", "العربية", "🇩🇿"),
    FRENCH("fr", "Français", "🇳🇪");

    val isArabic: Boolean get() = this == ARABIC
}
