package com.example.futebolsabado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val jogos: Int,
    val vitorias: Int,
    val golos: Int,
)
