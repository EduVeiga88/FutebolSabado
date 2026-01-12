package com.example.futebolsabado.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayerAvatar(
    modifier: Modifier = Modifier,
    player: Player,
    isSelected: Boolean
) {
    val bgColor = if (isSelected) Color.DarkGray else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(64.dp)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = player.nome.first().uppercase(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }

        Spacer(modifier = modifier.height(4.dp))

        Text(
            text = player.nome

        )
    }
}


@Preview
@Composable
private fun PlayerAvatarPreview() {
    FutebolSabadoTheme {
        PlayerAvatar(
            player = Player(
                id = 1,
                nome = "Pedro",
                jogos = 10,
                vitorias = 6,
                golos = 12
            ),
            modifier = Modifier,
            isSelected = true
        )
    }

}