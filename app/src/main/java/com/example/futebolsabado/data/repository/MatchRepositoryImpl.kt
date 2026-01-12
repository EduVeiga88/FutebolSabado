package com.example.futebolsabado.data.repository

import com.example.futebolsabado.data.dao.MatchDao
import com.example.futebolsabado.data.entity.MatchEntity
import com.example.futebolsabado.data.entity.MatchPlayerEntity
import com.example.futebolsabado.domain.model.CreateMatchRequest
import com.example.futebolsabado.domain.repository.MatchRepository
import javax.inject.Inject

private const val TEAM_COLETES = "COLETES"
private const val TEAM_SEM = "SEM_COLETES"

class MatchRepositoryImpl @Inject constructor(
    private val matchDao: MatchDao
) : MatchRepository {

    override suspend fun addMatch(request: CreateMatchRequest): Long {

        val scoreColetes = request.coletesIds.sumOf { id -> request.goalsByPlayer[id] ?: 0 }
        val scoreSem = request.semColetesIds.sumOf { id -> request.goalsByPlayer[id] ?: 0 }


        val match = MatchEntity(
            scoreColetes = scoreColetes,
            scoreSemColetes = scoreSem,
            createdAt = request.createdAt
        )


        val players = buildList {
            request.coletesIds.forEach { playerId ->
                add(
                    MatchPlayerEntity(
                        matchId = 0L,
                        playerId = playerId,
                        team = TEAM_COLETES,
                        goals = request.goalsByPlayer[playerId] ?: 0
                    )
                )
            }

            request.semColetesIds.forEach { playerId ->
                add(
                    MatchPlayerEntity(
                        matchId = 0L,
                        playerId = playerId,
                        team = TEAM_SEM,
                        goals = request.goalsByPlayer[playerId] ?: 0
                    )
                )
            }
        }


        return matchDao.insertMatchWithPlayers(match, players)
    }

}
