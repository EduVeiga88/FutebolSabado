package com.example.futebolsabado.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun GoalCounter(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEFEFEF))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CounterButton(text = "-", onClick = onDecrement)

        Text(
            text = count.toString(),
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        CounterButton(text = "+", onClick = onIncrement)
    }
}

@Composable
private fun CounterButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}



@Preview
@Composable
private fun GoalCounterPreview() {
    FutebolSabadoTheme {
        GoalCounter(
            count = 0,
            onIncrement = {},
            onDecrement = {}
        )
    }
}