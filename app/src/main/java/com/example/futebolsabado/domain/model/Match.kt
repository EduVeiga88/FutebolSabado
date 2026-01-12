package com.example.futebolsabado.domain.model

import java.time.LocalDateTime

data class Match(
    val id: Long = 0,
    val coletes: List<Player>,
    val semColetes: List<Player>,
    val goals: Map<Long, Int>,
    val scoreColetes: Int,
    val scoreSemColetes: Int,
    val date: LocalDateTime
)