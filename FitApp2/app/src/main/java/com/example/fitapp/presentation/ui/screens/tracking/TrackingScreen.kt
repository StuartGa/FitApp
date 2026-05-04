package com.example.fitapp.presentation.ui.screens.tracking

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitapp.presentation.ui.component.FitCard
import com.example.fitapp.presentation.ui.theme.HeartRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingScreen(
    viewModel: TrackingViewModel
) {
    val isTracking by viewModel.isTracking.collectAsState()
    val distance by viewModel.distanceMeters.collectAsState()
    val elapsed by viewModel.elapsedSeconds.collectAsState()
    val speed by viewModel.currentSpeed.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Activity Tracking", color = MaterialTheme.colorScheme.onSurface) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Map,
                    contentDescription = "Map placeholder",
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MetricItem(
                    icon = Icons.Default.Timer,
                    label = "Time",
                    value = formatTime(elapsed),
                    color = MaterialTheme.colorScheme.primary
                )
                MetricItem(
                    icon = Icons.Default.TrendingUp,
                    label = "Distance",
                    value = String.format("%.2f km", distance / 1000),
                    color = HeartRed
                )
                MetricItem(
                    icon = Icons.Default.Speed,
                    label = "Speed",
                    value = String.format("%.1f", speed) + " km/h",
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (isTracking) viewModel.stopTracking() else viewModel.startTracking()
                },
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isTracking) HeartRed else MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    if (isTracking) Icons.Default.Stop else Icons.Default.PlayArrow,
                    contentDescription = if (isTracking) "Stop" else "Start",
                    modifier = Modifier.size(36.dp)
                )
            }

            if (isTracking) {
                Spacer(modifier = Modifier.height(16.dp))
                FitCard(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MyLocation, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("GPS active - tracking your route", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

@Composable
fun MetricItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

fun formatTime(seconds: Long): String {
    val h = seconds / 3600
    val m = (seconds % 3600) / 60
    val s = seconds % 60
    return "%02d:%02d:%02d".format(h, m, s)
}
