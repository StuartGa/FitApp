package com.example.fitapp.presentation.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitapp.presentation.ui.component.DashboardTapGroup
import com.example.fitapp.presentation.ui.component.DateText
import com.example.fitapp.presentation.ui.component.FitCard
import com.example.fitapp.presentation.ui.theme.CalorieOrange
import com.example.fitapp.presentation.ui.theme.HeartRed
import com.example.fitapp.presentation.ui.theme.HeroNumberStyle
import com.example.fitapp.presentation.ui.theme.StepsGreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val today = LocalDate.now().format(
        DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es"))
    ).uppercase()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        CenterAlignedTopAppBar(
            title = { Text("FitApp", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

        DateText(text = today, modifier = Modifier.fillMaxWidth())

        DashboardTapGroup(listOf("Today", "Week", "Month", "Year"))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                icon = Icons.Default.DirectionsRun,
                value = "8,432",
                label = "Steps",
                color = StepsGreen,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                icon = Icons.Default.LocalFireDepartment,
                value = "420",
                label = "Calories",
                color = CalorieOrange,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                icon = Icons.Default.Timer,
                value = "32",
                label = "Active min",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                icon = Icons.Default.Favorite,
                value = "72",
                label = "Avg BPM",
                color = HeartRed,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        FitCard(
            title = "Weekly Activity",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            WeeklyBarChart(
                data = listOf(6200f, 8100f, 7500f, 9200f, 6800f, 10300f, 8432f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        FitCard(
            title = "Current Streak",
            subtitle = "Keep it up!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocalFireDepartment,
                    contentDescription = null,
                    tint = CalorieOrange,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "5",
                    style = HeroNumberStyle,
                    color = CalorieOrange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "days",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun MetricCard(
    icon: ImageVector,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    FitCard(modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun WeeklyBarChart(
    data: List<Float>,
    modifier: Modifier = Modifier
) {
    val labels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val maxValue = data.maxOrNull() ?: 1f

    Canvas(modifier = modifier.padding(top = 8.dp)) {
        val barCount = data.size
        val totalWidth = size.width
        val barWidth = (totalWidth / barCount) * 0.5f
        val spacing = (totalWidth / barCount) * 0.5f
        val chartHeight = size.height - 30f

        for (i in data.indices) {
            val barHeight = (data[i] / maxValue) * chartHeight
            val x = i * (barWidth + spacing) + spacing / 2
            val y = chartHeight - barHeight

            drawRect(
                color = MaterialTheme.colorScheme.primary,
                topLeft = Offset(x, y),
                size = Size(barWidth, barHeight)
            )
        }
    }
}
