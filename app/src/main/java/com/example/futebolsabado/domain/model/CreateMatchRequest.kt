package com.example.futebolsabado.domain.model

data class CreateMatchRequest(
    val coletesIds: List<Int>,
    val semColetesIds: List<Int>,
    val goalsByPlayer: Map<Int, Int>,
    val createdAt: Long
)
