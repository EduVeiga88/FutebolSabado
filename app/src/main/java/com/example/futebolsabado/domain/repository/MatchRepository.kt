package com.example.futebolsabado.domain.repository

import com.example.futebolsabado.data.entity.MatchEntity
import com.example.futebolsabado.domain.model.CreateMatchRequest
import com.example.futebolsabado.domain.model.Match
import com.example.futebolsabado.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun addMatch(request: CreateMatchRequest): Long

    suspend fun delete(id: Long)
    fun getAllMatchs(): Flow<List<Match>>

    suspend fun getMatchById(id: Long): Match?

}