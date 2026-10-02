package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.model.MapLandmark
import com.example.data.model.ProgramDay
import com.example.data.repository.ProgramRepository
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// Center point of Ghardaïa region
private val GHARDAIA_CENTER = GeoPoint(32.4850, 3.6780)
private const val DEFAULT_ZOOM = 11.5

@Composable
fun MapScreen(
    onNavigateToDay: (ProgramDay) -> Unit,
    isArabic: Boolean = true,
    onToggleLanguage: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedLandmark by remember { mutableStateOf<MapLandmark?>(null) }
    var selectedCategoryFilter by remember { mutableStateOf<String?>(null) }
    var mapViewInstance by remember { mutableStateOf<MapView?>(null) }

    val allLandmarks = ProgramRepository.mapLandmarks
    val filteredLandmarks = remember(selectedCategoryFilter) {
        if (selectedCategoryFilter == null) {
            allLandmarks
        } else {
            allLandmarks.filter { it.category.contains(selectedCategoryFilter!!) || it.categoryFr.contains(selectedCategoryFilter!!) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("map_screen_root")
    ) {
        // OpenStreetMap AndroidView
        AndroidView(
            factory = { ctx ->
                Configuration.getInstance().userAgentValue = ctx.packageName

                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    controller.setZoom(DEFAULT_ZOOM)
                    controller.setCenter(GHARDAIA_CENTER)
                    mapViewInstance = this
                }
            },
            update = { mapView ->
                mapViewInstance = mapView
                mapView.overlays.clear()

                filteredLandmarks.forEach { landmark ->
                    val marker = Marker(mapView).apply {
                        position = GeoPoint(landmark.latitude, landmark.longitude)
                        title = landmark.localizedName(isArabic)
                        snippet = landmark.localizedSubtitle(isArabic)
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = createCustomMarkerDrawable(context, landmark.markerColorHex)
                        setOnMarkerClickListener { _, _ ->
                            selectedLandmark = landmark
                            mapView.controller.animateTo(GeoPoint(landmark.latitude, landmark.longitude))
                            true
                        }
                    }
                    mapView.overlays.add(marker)
                }
                mapView.invalidate()
            },
            modifier = Modifier.fillMaxSize()
        )

        // Top Header & Category Filters
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            // Header Bar Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                shadowElevation = 4.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isArabic) "خريطة معالم ومسار الوفد النيجيري" else "Carte Interactive des Sites (Niger)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isArabic) "غرداية، بني يزقن، تافيلالت، متليلي، وزلفانة" else "Ghardaïa, Beni Isguen, Tafilalet, Metlili & Zelfana",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Language Toggle
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier
                            .clickable(onClick = onToggleLanguage)
                            .testTag("map_lang_toggle")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
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

                    Spacer(modifier = Modifier.width(4.dp))

                    // Reset Center Icon
                    IconButton(
                        onClick = {
                            mapViewInstance?.controller?.apply {
                                setZoom(DEFAULT_ZOOM)
                                animateTo(GHARDAIA_CENTER)
                            }
                            selectedLandmark = null
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .testTag("map_recenter_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = if (isArabic) "إعادة ضبط الخريطة" else "Recentrer la carte",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Quick Filters Strip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedCategoryFilter == null,
                    onClick = { selectedCategoryFilter = null },
                    label = { Text(if (isArabic) "كافة المواقع (${allLandmarks.size})" else "Tous les sites (${allLandmarks.size})", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White
                    )
                )

                FilterChip(
                    selected = selectedCategoryFilter == "الرحلة الأولى" || selectedCategoryFilter == "1ère Excursion",
                    onClick = {
                        val filter = if (isArabic) "الرحلة الأولى" else "1ère Excursion"
                        selectedCategoryFilter = if (selectedCategoryFilter == filter) null else filter
                    },
                    label = { Text(if (isArabic) "قصر غرداية وتافيلالت" else "Ghardaïa & Tafilalet", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFD84315),
                        selectedLabelColor = Color.White
                    )
                )

                FilterChip(
                    selected = selectedCategoryFilter == "الرحلة الثانية" || selectedCategoryFilter == "2ème Excursion",
                    onClick = {
                        val filter = if (isArabic) "الرحلة الثانية" else "2ème Excursion"
                        selectedCategoryFilter = if (selectedCategoryFilter == filter) null else filter
                    },
                    label = { Text(if (isArabic) "متليلي وسبسب" else "Metlili & Sebseb", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFB71C1C),
                        selectedLabelColor = Color.White
                    )
                )

                FilterChip(
                    selected = selectedCategoryFilter == "الرحلة الثالثة" || selectedCategoryFilter == "3ème Excursion",
                    onClick = {
                        val filter = if (isArabic) "الرحلة الثالثة" else "3ème Excursion"
                        selectedCategoryFilter = if (selectedCategoryFilter == filter) null else filter
                    },
                    label = { Text(if (isArabic) "حمام زلفانة" else "Station Zelfana", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF00838F),
                        selectedLabelColor = Color.White
                    )
                )

                FilterChip(
                    selected = selectedCategoryFilter == "الإقامة" || selectedCategoryFilter == "Résidence",
                    onClick = {
                        val filter = if (isArabic) "الإقامة" else "Résidence"
                        selectedCategoryFilter = if (selectedCategoryFilter == filter) null else filter
                    },
                    label = { Text(if (isArabic) "مقر واد نشو" else "Résidence Oued Nechou", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF00796B),
                        selectedLabelColor = Color.White
                    )
                )

                FilterChip(
                    selected = selectedCategoryFilter == "شعائر" || selectedCategoryFilter == "Culte",
                    onClick = {
                        val filter = if (isArabic) "شعائر" else "Culte"
                        selectedCategoryFilter = if (selectedCategoryFilter == filter) null else filter
                    },
                    label = { Text(if (isArabic) "مسجد عقبة بن نافع" else "Mosquée Oqba", fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2E7D32),
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // Floating Map Zoom Controls
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    mapViewInstance?.controller?.zoomIn()
                },
                modifier = Modifier
                    .size(42.dp)
                    .testTag("zoom_in_button"),
                containerColor = MaterialTheme.colorScheme.surface,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 3.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = if (isArabic) "تكبير الخريطة" else "Zoom avant",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp)
                )
            }

            FloatingActionButton(
                onClick = {
                    mapViewInstance?.controller?.zoomOut()
                },
                modifier = Modifier
                    .size(42.dp)
                    .testTag("zoom_out_button"),
                containerColor = MaterialTheme.colorScheme.surface,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 3.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = if (isArabic) "تصغير الخريطة" else "Zoom arrière",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Selected Landmark Interactive Detail Card at Bottom
        AnimatedVisibility(
            visible = selectedLandmark != null,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            selectedLandmark?.let { landmark ->
                ElevatedCard(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("landmark_map_popup_${landmark.id}")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(landmark.markerColorHex)
                            ) {
                                Text(
                                    text = landmark.localizedCategory(isArabic),
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            IconButton(
                                onClick = { selectedLandmark = null },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = if (isArabic) "إغلاق" else "Fermer",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            // Thumbnail
                            Image(
                                painter = painterResource(id = landmark.drawableRes),
                                contentDescription = landmark.localizedName(isArabic),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(74.dp)
                                    .clip(RoundedCornerShape(14.dp))
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = landmark.localizedName(isArabic),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = landmark.localizedSubtitle(isArabic),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                // Distance from residence
                                val distKm = calculateDistanceKm(
                                    32.5532, 3.6521,
                                    landmark.latitude, landmark.longitude
                                )
                                Text(
                                    text = if (isArabic)
                                        "المسافة من مقر واد نشو: ≈ ${"%.1f".format(distKm)} كم"
                                    else
                                        "Distance depuis Oued Nechou : ≈ ${"%.1f".format(distKm)} km",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = landmark.localizedDescription(isArabic),
                            fontSize = 12.sp,
                            lineHeight = 17.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Action Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Open in external maps (Google Maps / Navigation)
                            OutlinedButton(
                                onClick = {
                                    val gmmIntentUri = Uri.parse("geo:${landmark.latitude},${landmark.longitude}?q=${landmark.latitude},${landmark.longitude}(${Uri.encode(landmark.localizedName(isArabic))})")
                                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                    try {
                                        context.startActivity(mapIntent)
                                    } catch (_: Exception) {}
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Directions,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isArabic) "الملاحة" else "Itinéraire", fontSize = 12.sp)
                            }

                            // View in Program Schedule
                            if (landmark.associatedDayId != null) {
                                val day = ProgramRepository.programDays.find { it.id == landmark.associatedDayId }
                                    ?: if (landmark.associatedDayId == "program_friday_prayer") ProgramRepository.fridayPrayerProgram else null

                                if (day != null) {
                                    Button(
                                        onClick = {
                                            onNavigateToDay(day)
                                        },
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Text(if (isArabic) "جدول النشاط" else "Au programme", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mapViewInstance?.onDetach()
        }
    }
}

/**
 * Creates a clean custom pin drawable for OSMDroid marker
 */
private fun createCustomMarkerDrawable(context: Context, colorHex: Long): Drawable {
    val size = 64
    val bitmap = Bitmap.createBitmap(size, size + 16, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    val paint = Paint().apply {
        isAntiAlias = true
        color = colorHex.toInt()
        style = Paint.Style.FILL
    }

    // Outer circle
    val radius = size / 2.2f
    val centerX = size / 2f
    val centerY = radius

    // Drop pin pointer path
    val path = android.graphics.Path().apply {
        moveTo(centerX - radius * 0.8f, centerY + radius * 0.4f)
        lineTo(centerX, size + 14f)
        lineTo(centerX + radius * 0.8f, centerY + radius * 0.4f)
        close()
    }
    canvas.drawPath(path, paint)
    canvas.drawCircle(centerX, centerY, radius, paint)

    // Inner white dot
    val whitePaint = Paint().apply {
        isAntiAlias = true
        color = android.graphics.Color.WHITE
        style = Paint.Style.FILL
    }
    canvas.drawCircle(centerX, centerY, radius * 0.45f, whitePaint)

    return BitmapDrawable(context.resources, bitmap)
}

/**
 * Haversine distance formula in kilometers
 */
private fun calculateDistanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val r = 6371.0 // Earth radius in km
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return r * c
}
