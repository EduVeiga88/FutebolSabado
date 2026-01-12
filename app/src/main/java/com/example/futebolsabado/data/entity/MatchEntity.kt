package com.example.futebolsabado.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Match")
data class MatchEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val scoreColetes: Int,
        val scoreSemColetes: Int,
        val createdAt: Long
    )
