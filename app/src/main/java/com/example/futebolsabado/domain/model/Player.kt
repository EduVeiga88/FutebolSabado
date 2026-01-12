package com.example.futebolsabado.domain.model

data class Player(
    val id: Int,
    val nome: String,
    val jogos: Int,
    val vitorias: Int,
    val golos: Int,
)