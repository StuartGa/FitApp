package com.example.fitapp.presentation.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitapp.presentation.ui.component.FitCard
import com.example.fitapp.presentation.ui.theme.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val themeMode by viewModel.themeMode.collectAsState()
    val unitSystem by viewModel.unitSystem.collectAsState()
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("Settings", color = MaterialTheme.colorScheme.onSurface) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
        )

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
            FitCard(title = "Appearance", modifier = Modifier.fillMaxWidth()) {
                SettingsRow(
                    icon = Icons.Default.DarkMode,
                    title = "Theme",
                    subtitle = when (themeMode) {
                        0 -> "Light"
                        1 -> "Dark"
                        2 -> "OLED Dark"
                        else -> "Dark"
                    },
                    onClick = { viewModel.cycleTheme() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            FitCard(title = "Preferences", modifier = Modifier.fillMaxWidth()) {
                SettingsRow(
                    icon = Icons.Default.Straighten,
                    title = "Units",
                    subtitle = if (unitSystem == "metric") "Metric (kg, km)" else "Imperial (lb, mi)",
                    onClick = { viewModel.toggleUnits() }
                )
                Spacer(modifier = Modifier.height(12.dp))
                SettingsToggleRow(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    subtitle = "Workout reminders",
                    checked = notificationsEnabled,
                    onToggle = { notificationsEnabled = it }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            FitCard(title = "About", modifier = Modifier.fillMaxWidth()) {
                SettingsRow(
                    icon = Icons.Default.Share,
                    title = "Share FitApp",
                    subtitle = "Tell your friends",
                    onClick = { }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "FitApp v1.0 - Built with Jetpack Compose",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SettingsRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(20.dp))
    }
}

@Composable
fun SettingsToggleRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = onToggle)
    }
}
