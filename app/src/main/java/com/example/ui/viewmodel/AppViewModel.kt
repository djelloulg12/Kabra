package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.GeminiChatService
import com.example.data.model.ChatMessage
import com.example.data.model.MessageSender
import com.example.data.model.Participant
import com.example.data.model.ProgramDay
import com.example.data.repository.ProgramRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

enum class AppTab(val titleAr: String, val titleFr: String, val iconTag: String) {
    SCHEDULE("البرنامج والأيام", "Programme", "calendar"),
    MAP("خريطة المعالم", "Carte", "map"),
    LANDMARKS("المعالم والصور", "Patrimoine", "landmark"),
    AI_PARTNER("شريك المحادثة الذكي", "Assistant IA", "chat"),
    PARTICIPANTS("وفد المشاركين (39)", "Délégation", "people"),
    ABOUT("عن البرنامج والديباجة", "À propos", "info")
}

class AppViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val _currentLanguage = MutableStateFlow(com.example.data.model.AppLanguage.ARABIC)
    val currentLanguage: StateFlow<com.example.data.model.AppLanguage> = _currentLanguage.asStateFlow()

    private val _currentTab = MutableStateFlow(AppTab.SCHEDULE)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    // Day detail screen state
    private val _selectedDay = MutableStateFlow<ProgramDay?>(null)
    val selectedDay: StateFlow<ProgramDay?> = _selectedDay.asStateFlow()

    // Participants search and filter
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedSpecialty = MutableStateFlow<String?>(null)
    val selectedSpecialty: StateFlow<String?> = _selectedSpecialty.asStateFlow()

    private val _selectedGender = MutableStateFlow<String?>(null)
    val selectedGender: StateFlow<String?> = _selectedGender.asStateFlow()

    // Chat AI state
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                sender = MessageSender.AI_PARTNER,
                text = "السلام عليكم ورحمة الله! Bonjour à toute la délégation du Niger! 🇩🇿🤝🇳🇪\nأنا مرشدكم وشريككم الذكي للمحادثة والممارسة اللغوية في غرداية. يمكنك التدرب معي على التحدث بالعربية والفرنسية، أو سؤالي عن تفاصيل أي يوم في البرنامج ومبرراته!",
                translation = "Bonjour et bienvenue à toute la délégation nigérienne! Je suis votre partenaire linguistique et guide culturel interactif à Ghardaïa.",
                phoneticPronunciation = "Assalamu alaykum wa rahmatullah! Bienvenue!"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isAiTyping = MutableStateFlow(false)
    val isAiTyping: StateFlow<Boolean> = _isAiTyping.asStateFlow()

    // Text to speech
    private var textToSpeech: TextToSpeech? = null
    private var isTtsReady = false

    init {
        try {
            textToSpeech = TextToSpeech(application, this)
        } catch (_: Exception) {}
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsReady = true
            try {
                textToSpeech?.language = Locale.forLanguageTag("ar")
            } catch (_: Exception) {}
        }
    }

    fun setLanguage(lang: com.example.data.model.AppLanguage) {
        _currentLanguage.value = lang
    }

    fun toggleLanguage() {
        _currentLanguage.value = if (_currentLanguage.value.isArabic) com.example.data.model.AppLanguage.FRENCH else com.example.data.model.AppLanguage.ARABIC
    }

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun selectDay(day: ProgramDay?) {
        _selectedDay.value = day
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSpecialtyFilter(specialty: String?) {
        _selectedSpecialty.value = specialty
    }

    fun setGenderFilter(gender: String?) {
        _selectedGender.value = gender
    }

    fun getFilteredParticipants(): List<Participant> {
        val query = _searchQuery.value.trim().lowercase()
        val specialty = _selectedSpecialty.value
        val gender = _selectedGender.value

        return ProgramRepository.participantsList.filter { p ->
            val matchesQuery = query.isEmpty() ||
                    p.fullName.lowercase().contains(query) ||
                    p.specialty.lowercase().contains(query)

            val matchesSpecialty = specialty == null || p.specialty == specialty
            val matchesGender = gender == null || p.gender.equals(gender, ignoreCase = true)

            matchesQuery && matchesSpecialty && matchesGender
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank() || _isAiTyping.value) return

        val userMsg = ChatMessage(
            sender = MessageSender.USER,
            text = text.trim()
        )
        _chatMessages.value = _chatMessages.value + userMsg
        _isAiTyping.value = true

        viewModelScope.launch {
            val history = _chatMessages.value.takeLast(6).map {
                val role = if (it.sender == MessageSender.USER) "user" else "model"
                Pair(role, it.text)
            }

            val reply = GeminiChatService.sendMessage(history, text.trim())

            val aiMsg = ChatMessage(
                sender = MessageSender.AI_PARTNER,
                text = reply
            )
            _chatMessages.value = _chatMessages.value + aiMsg
            _isAiTyping.value = false
        }
    }

    fun speakText(text: String) {
        if (!isTtsReady || textToSpeech == null) return
        try {
            // Clean markdown tokens for clear speech
            val clean = text.replace("*", "").replace("#", "")
            // Detect if mostly French or Arabic
            val hasArabic = clean.any { it in '\u0600'..'\u06FF' }
            if (hasArabic) {
                textToSpeech?.language = Locale.forLanguageTag("ar")
            } else {
                textToSpeech?.language = Locale.FRENCH
            }
            textToSpeech?.speak(clean, TextToSpeech.QUEUE_FLUSH, null, "UtteranceId_${System.currentTimeMillis()}")
        } catch (_: Exception) {}
    }

    fun stopSpeaking() {
        try {
            textToSpeech?.stop()
        } catch (_: Exception) {}
    }

    override fun onCleared() {
        super.onCleared()
        try {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        } catch (_: Exception) {}
    }
}
