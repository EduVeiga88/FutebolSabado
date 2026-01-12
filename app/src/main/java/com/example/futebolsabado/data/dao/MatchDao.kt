package com.example.futebolsabado.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.futebolsabado.data.entity.MatchEntity
import com.example.futebolsabado.data.entity.MatchPlayerEntity

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMatch(match: MatchEntity) : Long

    @Insert
    suspend fun insertMatchPlayers(players: List<MatchPlayerEntity>)

    @Delete
    suspend fun delete (match: MatchEntity)

    @Transaction
    suspend fun insertMatchWithPlayers(
        match: MatchEntity,
        players: List<MatchPlayerEntity>
    ): Long {
        val matchId = insertMatch(match)
        val playersWithMatchId = players.map { p ->
            p.copy(matchId = matchId)
        }

        insertMatchPlayers(playersWithMatchId)

        return matchId
    }

}