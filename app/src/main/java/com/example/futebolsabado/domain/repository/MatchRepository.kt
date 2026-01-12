package com.example.futebolsabado.domain.repository

import com.example.futebolsabado.domain.model.CreateMatchRequest

interface MatchRepository {
    suspend fun addMatch(request: CreateMatchRequest): Long

}