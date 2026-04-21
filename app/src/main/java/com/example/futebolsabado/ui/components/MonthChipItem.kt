package com.example.futebolsabado.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthChipItem(
    monthLabel: String,
    isPaid: Boolean,
    onToggle:() -> Unit
) {
    FilterChip(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        selected = isPaid,
        onClick = onToggle,
        label = {Text(monthLabel)},
        leadingIcon = {
            if (isPaid){
                Icon(Icons.Default.Check, contentDescription = "Pago")
            }
        },
    )
}