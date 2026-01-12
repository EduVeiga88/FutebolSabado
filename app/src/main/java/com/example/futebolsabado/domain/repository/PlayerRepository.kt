package com.example.futebolsabado.domain.repository

import com.example.futebolsabado.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun insert(nome: String,jogos: Int,vitorias: Int,golos: Int)

    suspend fun update(id: Int)

    suspend fun delete(id: Int)

    fun getAll(): Flow<List<Player>>

    suspend fun getBy(id: Int): Player?

}