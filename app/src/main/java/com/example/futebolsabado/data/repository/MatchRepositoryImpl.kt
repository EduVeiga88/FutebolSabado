package com.example.futebolsabado.data.repository

import com.example.futebolsabado.data.dao.MatchDao
import com.example.futebolsabado.data.entity.MatchEntity
import com.example.futebolsabado.data.entity.MatchPlayerEntity
import com.example.futebolsabado.domain.model.CreateMatchRequest
import com.example.futebolsabado.domain.model.Match
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun delete(id: Long) {
        val existingMatch = matchDao.getMatchById(id) ?: return
        matchDao.delete(existingMatch)
    }

    override fun getAllMatchs(): Flow<List<Match>> {
        return matchDao.getAllMatchs().map {entities ->
            entities.map {entity ->
                Match(
                    id = entity.id,
                    scoreColetes = entity.scoreColetes,
                    scoreSemColetes = entity.scoreSemColetes,
                    createdAt = entity.createdAt,
                    coletes = emptyList(),
                    semColetes = emptyList(),
                    goals = emptyMap()
                )

            }

        }
    }

    override suspend fun getMatchById(id: Long): Match? {
        return matchDao.getMatchById(id)?.let {entity ->
            Match(
                id = entity.id,
                scoreColetes = entity.scoreColetes,
                scoreSemColetes = entity.scoreSemColetes,
                createdAt = entity.createdAt,
                coletes = emptyList(),
                semColetes = emptyList(),
                goals = emptyMap()
            )
        }
    }
}
