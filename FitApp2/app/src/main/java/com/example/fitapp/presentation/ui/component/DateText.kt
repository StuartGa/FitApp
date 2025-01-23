package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.domain.utils.Utils
import com.example.fitapp.presentation.ui.theme.FitAppTheme


@Composable
fun DateText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Light,
        modifier = modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DateTextPreview() {
    FitAppTheme {
        DateText(text =  Utils.getFormattedDate())
    }
}