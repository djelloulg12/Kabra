package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import com.example.data.repository.ProgramRepository
import com.example.ui.screens.AboutProgramScreen
import com.example.ui.screens.ConversationAiScreen
import com.example.ui.screens.DayDetailScreen
import com.example.ui.screens.DaysScheduleScreen
import com.example.ui.screens.LandmarksScreen
import com.example.ui.screens.MapScreen
import com.example.ui.screens.ParticipantsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val currentLanguage by viewModel.currentLanguage.collectAsState()
                val layoutDirection = if (currentLanguage.isArabic) LayoutDirection.Rtl else LayoutDirection.Ltr

                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                    MainAppContent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: AppViewModel) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val isArabic = currentLanguage.isArabic

    val currentTab by viewModel.currentTab.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    val chatMessages by viewModel.chatMessages.collectAsState()
    val isAiTyping by viewModel.isAiTyping.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedSpecialty by viewModel.selectedSpecialty.collectAsState()
    val selectedGender by viewModel.selectedGender.collectAsState()

    // If a day is selected, show the DayDetailScreen
    if (selectedDay != null) {
        DayDetailScreen(
            day = selectedDay!!,
            onBack = { viewModel.selectDay(null) },
            onAskAiAboutDay = { query ->
                viewModel.selectDay(null)
                viewModel.selectTab(AppTab.AI_PARTNER)
                viewModel.sendMessage(query)
            },
            onSpeakText = { text -> viewModel.speakText(text) },
            isArabic = isArabic,
            onToggleLanguage = { viewModel.toggleLanguage() }
        )
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.testTag("main_navigation_bar")
            ) {
                AppTab.entries.forEach { tab ->
                    val isSelected = currentTab == tab
                    val icon = when (tab) {
                        AppTab.SCHEDULE -> Icons.Default.CalendarMonth
                        AppTab.MAP -> Icons.Default.Map
                        AppTab.LANDMARKS -> Icons.Default.Place
                        AppTab.AI_PARTNER -> Icons.AutoMirrored.Filled.Chat
                        AppTab.PARTICIPANTS -> Icons.Default.Groups
                        AppTab.ABOUT -> Icons.Default.Info
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.selectTab(tab) },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (isArabic) tab.titleAr else tab.titleFr
                            )
                        },
                        label = {
                            Text(
                                text = when (tab) {
                                    AppTab.SCHEDULE -> if (isArabic) "البرنامج" else "Programme"
                                    AppTab.MAP -> if (isArabic) "الخريطة" else "Carte"
                                    AppTab.LANDMARKS -> if (isArabic) "المعالم" else "Patrimoine"
                                    AppTab.AI_PARTNER -> if (isArabic) "المحادثة" else "Assistant IA"
                                    AppTab.PARTICIPANTS -> if (isArabic) "الوفد (39)" else "Délégation"
                                    AppTab.ABOUT -> if (isArabic) "عن الدليل" else "À propos"
                                },
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentTab,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            modifier = Modifier.padding(innerPadding),
            label = "tab_transition"
        ) { targetTab ->
            when (targetTab) {
                AppTab.SCHEDULE -> {
                    DaysScheduleScreen(
                        days = ProgramRepository.programDays,
                        onSelectDay = { day -> viewModel.selectDay(day) },
                        onOpenMap = { viewModel.selectTab(AppTab.MAP) },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }

                AppTab.MAP -> {
                    MapScreen(
                        onNavigateToDay = { day ->
                            viewModel.selectDay(day)
                        },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }

                AppTab.LANDMARKS -> {
                    LandmarksScreen(
                        landmarks = ProgramRepository.culturalLandmarks,
                        onSpeak = { text -> viewModel.speakText(text) },
                        onOpenMap = { viewModel.selectTab(AppTab.MAP) },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }

                AppTab.AI_PARTNER -> {
                    ConversationAiScreen(
                        messages = chatMessages,
                        isTyping = isAiTyping,
                        onSendMessage = { query -> viewModel.sendMessage(query) },
                        onSpeak = { text -> viewModel.speakText(text) },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }

                AppTab.PARTICIPANTS -> {
                    val filtered = viewModel.getFilteredParticipants()
                    ParticipantsScreen(
                        participants = filtered,
                        searchQuery = searchQuery,
                        onSearchChange = { viewModel.setSearchQuery(it) },
                        selectedSpecialty = selectedSpecialty,
                        onSelectSpecialty = { viewModel.setSpecialtyFilter(it) },
                        selectedGender = selectedGender,
                        onSelectGender = { viewModel.setGenderFilter(it) },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }

                AppTab.ABOUT -> {
                    AboutProgramScreen(
                        onNavigateToFridayPrayer = {
                            viewModel.selectDay(ProgramRepository.fridayPrayerProgram)
                        },
                        isArabic = isArabic,
                        onToggleLanguage = { viewModel.toggleLanguage() }
                    )
                }
            }
        }
    }
}
