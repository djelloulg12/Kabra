package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.EventCategory
import com.example.data.model.ProgramDay
import com.example.data.repository.ProgramRepository
import com.example.ui.components.DayCard

@Composable
fun DaysScheduleScreen(
    days: List<ProgramDay>,
    onSelectDay: (ProgramDay) -> Unit,
    onOpenMap: () -> Unit = {},
    isArabic: Boolean = true,
    onToggleLanguage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedCategoryFilter by remember { mutableStateOf<EventCategory?>(null) }

    val filteredDays = remember(selectedCategoryFilter, days) {
        if (selectedCategoryFilter == null) {
            days
        } else {
            days.filter { it.category == selectedCategoryFilter }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Official Algerian - Nigerien Delegation Hero Header
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF004D40),
                                    Color(0xFF00695C),
                                    Color(0xFF00796B)
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top bar inside Hero with Flags and Language Switcher
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    shadowElevation = 2.dp
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_flag_algeria),
                                        contentDescription = "علم الجمهورية الجزائرية الديمقراطية الشعبية",
                                        modifier = Modifier
                                            .size(width = 30.dp, height = 20.dp)
                                            .clip(RoundedCornerShape(4.dp)),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Text(
                                    text = "🤝",
                                    fontSize = 15.sp
                                )
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    shadowElevation = 2.dp
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_flag_niger),
                                        contentDescription = "علم جمهورية النيجر",
                                        modifier = Modifier
                                            .size(width = 30.dp, height = 20.dp)
                                            .clip(RoundedCornerShape(4.dp)),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                            }

                            // Language toggle button
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White.copy(alpha = 0.2f),
                                modifier = Modifier
                                    .clickable(onClick = onToggleLanguage)
                                    .testTag("schedule_lang_toggle")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Language,
                                        contentDescription = null,
                                        tint = Color(0xFFFFD54F),
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (isArabic) "العربية 🇩🇿 (تبديل)" else "Français 🇳🇪 (Changer)",
                                        fontSize = 11.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (isArabic) "الجمهورية الجزائرية الديمقراطية الشعبية" else "République Algérienne Démocratique et Populaire",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = if (isArabic) "وزارة التكوين والتعليم المهنيين • ولاية غرداية" else "Ministère de la Formation et de l'Enseignement Professionnels • Wilaya de Ghardaïa",
                            color = Color(0xFFFFD54F),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (isArabic) "مشروع البرنامج الثقافي والسياحي والترفيهي" else "Projet de Programme Culturel, Touristique et Récréatif",
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = if (isArabic) "لفائدة وفد رعايا جمهورية النيجر الشقيقة" else "Au profit de la délégation des ressortissants de la République du Niger",
                            color = Color.White.copy(alpha = 0.95f),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Stats pills strip
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatPill(
                                title = if (isArabic) "المدة" else "Durée",
                                value = if (isArabic) "45 يوماً" else "45 Jours"
                            )
                            StatPill(
                                title = if (isArabic) "الوفد" else "Délégation",
                                value = if (isArabic) "39 متكوناً" else "39 Membres"
                            )
                            StatPill(
                                title = if (isArabic) "المقر" else "Résidence",
                                value = if (isArabic) "واد نشو" else "Oued Nechou"
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Map CTA Button
                        Button(
                            onClick = onOpenMap,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD54F)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("open_map_from_schedule_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = null,
                                tint = Color(0xFF004D40),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isArabic) "استعراض مسار ومعالم الوفد على الخريطة التفاعلية" else "Voir le circuit et les repères sur la carte interactive",
                                color = Color(0xFF004D40),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Weekly Friday Prayer Highlight Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clickable { onSelectDay(ProgramRepository.fridayPrayerProgram) }
                    .testTag("friday_prayer_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),
                border = BorderStroke(1.5.dp, Color(0xFF2E7D32).copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.size(46.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Mosque,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF2E7D32)
                            ) {
                                Text(
                                    text = if (isArabic) "موعد أسبوعي ثابت" else "Hebdomadaire",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isArabic) "كل يوم جمعة" else "Chaque Vendredi",
                                fontSize = 11.sp,
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isArabic) "أداء صلاة الجمعة بمسجد «عقبة بن نافع»" else "Prière du Vendredi : Mosquée « Oqba Ibn Nafi »",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF1B5E20)
                        )
                        Text(
                            text = if (isArabic)
                                "حي بوهراوة العليا • تعزيز الروابط الروحية والتعارف مع المصلين"
                            else
                                "Cité Bouhraoua • Fraternité spirituelle et communion fraternelle",
                            fontSize = 11.sp,
                            color = Color(0xFF33691E)
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFF2E7D32),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Section Title & Category Filter Chips
        item {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isArabic) "دليل الأيام والبرنامج الزمني" else "Programme des Journées",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = if (isArabic) "${filteredDays.size} محطات" else "${filteredDays.size} dates",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Scrollable category filters
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedCategoryFilter == null,
                        onClick = { selectedCategoryFilter = null },
                        label = { Text(if (isArabic) "الكل (${days.size})" else "Tous (${days.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.EXCURSION,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.EXCURSION) null else EventCategory.EXCURSION
                        },
                        label = { Text(if (isArabic) "الرحلات السياحية" else "Excursions (3)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.FOLKLORE,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.FOLKLORE) null else EventCategory.FOLKLORE
                        },
                        label = { Text(if (isArabic) "السهرات الفلكلورية" else "Soirées Folkloriques (3)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.WELCOME,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.WELCOME) null else EventCategory.WELCOME
                        },
                        label = { Text(if (isArabic) "الاستقبال والتوديع" else "Accueil & Départs") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.CINEMA_HISTORY,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.CINEMA_HISTORY) null else EventCategory.CINEMA_HISTORY
                        },
                        label = { Text(if (isArabic) "تاريخ وذاكرة (1 نوفمبر)" else "Mémoire & Histoire") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.RELAXATION,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.RELAXATION) null else EventCategory.RELAXATION
                        },
                        label = { Text(if (isArabic) "حمام زلفانة" else "Station Zelfana") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedCategoryFilter == EventCategory.CEREMONY,
                        onClick = {
                            selectedCategoryFilter = if (selectedCategoryFilter == EventCategory.CEREMONY) null else EventCategory.CEREMONY
                        },
                        label = { Text(if (isArabic) "الحفل الختامي" else "Cérémonie de Clôture") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }

        // List of Day Cards
        items(filteredDays, key = { it.id }) { day ->
            DayCard(
                day = day,
                onClick = { onSelectDay(day) },
                isArabic = isArabic,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatPill(title: String, value: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White.copy(alpha = 0.15f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
