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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SolarPower
import androidx.compose.material.icons.filled.WbIncandescent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.Participant
import com.example.data.repository.ProgramRepository

@Composable
fun ParticipantsScreen(
    participants: List<Participant>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedSpecialty: String?,
    onSelectSpecialty: (String?) -> Unit,
    selectedGender: String?,
    onSelectGender: (String?) -> Unit,
    isArabic: Boolean = true,
    onToggleLanguage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val solarCount = rememberCount(ProgramRepository.participantsList) { it.specialty.contains("طاقة") }
    val elecCount = rememberCount(ProgramRepository.participantsList) { it.specialty.contains("كهرباء") }
    val femaleCount = rememberCount(ProgramRepository.participantsList) { !it.isMale }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Hero Card with Both National Flags (Algeria 🇩🇿 & Niger 🇳🇪)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag("participants_header_card"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Flags Banner Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Flag 1: People's Democratic Republic of Algeria
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                shadowElevation = 3.dp,
                                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_flag_algeria),
                                    contentDescription = "علم الجمهورية الجزائرية الديمقراطية الشعبية",
                                    modifier = Modifier
                                        .size(width = 46.dp, height = 30.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                            Column {
                                Text(
                                    text = if (isArabic) "الجزائر" else "Algérie",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (isArabic) "بلد الاستقبال" else "Pays d'accueil",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Partnership emblem
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(34.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "🤝", fontSize = 16.sp)
                            }
                        }

                        // Flag 2: Republic of Niger
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = if (isArabic) "النيجر" else "Niger",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (isArabic) "الوفد الشقيق" else "Délégation",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                shadowElevation = 3.dp,
                                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_flag_niger),
                                    contentDescription = "علم جمهورية النيجر",
                                    modifier = Modifier
                                        .size(width = 46.dp, height = 30.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isArabic) "دليل أعضاء وفد جمهورية النيجر" else "Membres de la Délégation Nigérienne",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isArabic)
                                    "قائمة المشاركين الـ 39 المستفيدين من دورة التكوين والرسكلة بغرداية 2026"
                                else
                                    "Liste des 39 stagiaires en formation technique à Ghardaïa 2026",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Language Toggle
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier
                                .clickable(onClick = onToggleLanguage)
                                .testTag("participants_lang_toggle")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Language,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isArabic) "AR" else "FR",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Stats overview
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StatItem(
                                label = if (isArabic) "إجمالي الوفد" else "Total",
                                value = "${ProgramRepository.participantsList.size}",
                                color = MaterialTheme.colorScheme.primary
                            )
                            StatItem(
                                label = if (isArabic) "طاقة شمسية" else "Solaire",
                                value = "$solarCount",
                                color = Color(0xFFE65100)
                            )
                            StatItem(
                                label = if (isArabic) "كهرباء معمار" else "Bâtiment",
                                value = "$elecCount",
                                color = Color(0xFF0D47A1)
                            )
                            StatItem(
                                label = if (isArabic) "المتكونات الإناث" else "Femmes",
                                value = "$femaleCount",
                                color = Color(0xFFC2185B)
                            )
                        }
                    }
                }
            }
        }

        // Search Field
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchChange,
                    placeholder = {
                        Text(
                            if (isArabic) "ابحث بالاسم أو التخصص..." else "Rechercher par nom ou spécialité...",
                            fontSize = 12.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = if (isArabic) "مسح" else "Effacer"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("participant_search_field"),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Specialty & Gender Filters
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedSpecialty == null && selectedGender == null,
                        onClick = {
                            onSelectSpecialty(null)
                            onSelectGender(null)
                        },
                        label = { Text(if (isArabic) "كافة الأعضاء (${participants.size})" else "Tous (${participants.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedSpecialty == "تركيب ألواح الطاقة الشمسية",
                        onClick = {
                            onSelectSpecialty(
                                if (selectedSpecialty == "تركيب ألواح الطاقة الشمسية") null else "تركيب ألواح الطاقة الشمسية"
                            )
                        },
                        label = { Text(if (isArabic) "طاقة شمسية ($solarCount)" else "Solaire ($solarCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFE65100),
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedSpecialty == "كهرباء المعمار",
                        onClick = {
                            onSelectSpecialty(
                                if (selectedSpecialty == "كهرباء المعمار") null else "كهرباء المعمار"
                            )
                        },
                        label = { Text(if (isArabic) "كهرباء المعمار ($elecCount)" else "Bâtiment ($elecCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF0D47A1),
                            selectedLabelColor = Color.White
                        )
                    )

                    FilterChip(
                        selected = selectedGender == "F",
                        onClick = {
                            onSelectGender(if (selectedGender == "F") null else "F")
                        },
                        label = { Text(if (isArabic) "الإناث ($femaleCount)" else "Femmes ($femaleCount)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFC2185B),
                            selectedLabelColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        // Participant items without passport or phone numbers
        items(participants, key = { it.id }) { participant ->
            ParticipantCard(
                participant = participant,
                isArabic = isArabic,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ParticipantCard(
    participant: Participant,
    isArabic: Boolean = true,
    modifier: Modifier = Modifier
) {
    val isSolar = participant.specialty.contains("طاقة")
    val specialtyColor = if (isSolar) Color(0xFFE65100) else Color(0xFF1565C0)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("participant_item_${participant.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Index & Avatar badge
            Surface(
                shape = CircleShape,
                color = if (participant.isMale) MaterialTheme.colorScheme.primaryContainer else Color(0xFFFCE4EC),
                modifier = Modifier.size(46.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (participant.isMale) Icons.Default.Male else Icons.Default.Female,
                        contentDescription = null,
                        tint = if (participant.isMale) MaterialTheme.colorScheme.primary else Color(0xFFC2185B),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        ) {
                            Text(
                                text = "#${participant.id}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = participant.fullName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Gender badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = participant.localizedGender(isArabic),
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Specialty pill
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = specialtyColor.copy(alpha = 0.12f),
                        border = BorderStroke(0.5.dp, specialtyColor.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isSolar) Icons.Default.SolarPower else Icons.Default.WbIncandescent,
                                contentDescription = null,
                                tint = specialtyColor,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = participant.localizedSpecialty(isArabic),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = specialtyColor
                            )
                        }
                    }

                    // National Flag of Niger delegation badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFFFF3E0),
                        border = BorderStroke(0.5.dp, Color(0xFFFFB74D))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_flag_niger),
                                contentDescription = "علم النيجر",
                                modifier = Modifier
                                    .size(width = 16.dp, height = 11.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                contentScale = ContentScale.FillBounds
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isArabic) "وفد النيجر" else "Niger",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFE65100)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = color
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private inline fun rememberCount(list: List<Participant>, predicate: (Participant) -> Boolean): Int {
    return list.count(predicate)
}
