package com.example.futebolsabado.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MatchPlayer")
data class MatchPlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val matchId: Long,
    val playerId: Int,
    val team: String,
    val goals: Int


)
