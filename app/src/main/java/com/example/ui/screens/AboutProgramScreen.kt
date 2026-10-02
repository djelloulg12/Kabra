package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.repository.ProgramRepository

@Composable
fun AboutProgramScreen(
    onNavigateToFridayPrayer: () -> Unit,
    isArabic: Boolean = true,
    onToggleLanguage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Presidential & Ministerial Header Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag("about_program_header"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Flags of Algeria and Niger
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                shadowElevation = 2.dp,
                                border = BorderStroke(0.5.dp, Color.LightGray.copy(alpha = 0.5f))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_flag_algeria),
                                    contentDescription = "علم الجمهورية الجزائرية الديمقراطية الشعبية",
                                    modifier = Modifier
                                        .size(width = 32.dp, height = 21.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                            Text(text = "🤝", fontSize = 14.sp)
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                shadowElevation = 2.dp,
                                border = BorderStroke(0.5.dp, Color.LightGray.copy(alpha = 0.5f))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_flag_niger),
                                    contentDescription = "علم جمهورية النيجر",
                                    modifier = Modifier
                                        .size(width = 32.dp, height = 21.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }

                        // Language Toggle Button
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier
                                .clickable(onClick = onToggleLanguage)
                                .testTag("about_lang_toggle")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Language,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isArabic) "🇩🇿 العربية" else "🇳🇪 Français",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isArabic) "الجمهورية الجزائرية الديمقراطية الشعبية" else "République Algérienne Démocratique et Populaire",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (isArabic) "وزارة التكوين والتعليم المهنيين • ولاية غرداية" else "Ministère de la Formation et de l'Enseignement Professionnels • Wilaya de Ghardaïa",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isArabic) "مشروع البرنامج الثقافي والسياحي والترفيهي" else "Projet de Programme Culturel, Touristique et Récréatif",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isArabic) "لفائدة وفد من رعايا جمهورية النيجر الشقيقة 🇩🇿🤝🇳🇪" else "Au profit de la délégation des ressortissants de la République du Niger 🇩🇿🤝🇳🇪",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        // Preamble ("أولاً: الديباجة")
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (isArabic) "أولاً: الديباجة الرسمية" else "1. Préambule Officiel",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = ProgramRepository.getProgramOverview(isArabic),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Public,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (isArabic)
                                    "ملاحظة تنظيمية: تخصص أيام الأسبوع للتكوين والرسكلة الميدانية، وتخصص عطلات نهاية الأسبوع للأنشطة الثقافية والسياحية لضمان راحة الوفد واستمرارية التكوين."
                                else
                                    "Note d'organisation : Les jours ouvrables sont dédiés à la formation et au recyclage pratique, tandis que les week-ends sont consacrés aux découvertes culturelles et récréatives.",
                                fontSize = 11.sp,
                                lineHeight = 17.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }

        // Program Parameters & Locations
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = if (isArabic) "بيانات ومقرات البرنامج" else "Données & Lieux du Programme",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InfoRow(
                        icon = Icons.Default.School,
                        title = if (isArabic) "مدة البرنامج" else "Durée du Programme",
                        value = if (isArabic) "45 يوماً كاملة من التكوين والأنشطة المرافقة" else "45 jours complets de formation et d'activités"
                    )

                    InfoRow(
                        icon = Icons.Default.Apartment,
                        title = if (isArabic) "مكان إقامة الذكور" else "Résidence (Hommes)",
                        value = if (isArabic) "مركز التكوين المهني والتمهين واد نشو" else "CFPA d'Oued Nechou"
                    )

                    InfoRow(
                        icon = Icons.Default.Apartment,
                        title = if (isArabic) "مكان إقامة الإناث" else "Résidence (Femmes)",
                        value = if (isArabic) "معهد التعليم المهني واد نشو" else "Institut d'Enseignement Professionnel (IEP) Oued Nechou"
                    )

                    InfoRow(
                        icon = Icons.Default.Shield,
                        title = if (isArabic) "الشراكة والجهات المساهمة" else "Partenaires & Institutions",
                        value = if (isArabic)
                            "شركة الطاقات المتجددة (SKTM)، قطاعات الثقافة، السياحة، الشباب والرياضة، الصحة، الحماية المدنية والنقل"
                        else
                            "Société des Énergies Renouvelables (SKTM), Culture, Tourisme, Jeunesse et Sports, Santé, Protection Civile et Transports"
                    )
                }
            }
        }

        // Friday Prayer Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable(onClick = onNavigateToFridayPrayer),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),
                border = BorderStroke(1.dp, Color(0xFF2E7D32).copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Mosque,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (isArabic) "رابعاً: أداء صلاة الجمعة بمسجد عقبة بن نافع" else "Prière du Vendredi à la Mosquée Oqba Ibn Nafi",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1B5E20)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = ProgramRepository.fridayPrayerProgram.localizedJustification(isArabic),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(32.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
