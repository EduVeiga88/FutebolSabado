package com.example.futebolsabado.domain

data class Player(
    val id: Int,
    val nome: String,
    val jogos: Int,
    val vitorias: Int,
    val golos: Int,
)
