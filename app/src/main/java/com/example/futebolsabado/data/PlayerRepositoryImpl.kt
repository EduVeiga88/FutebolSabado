package com.example.futebolsabado.data

import com.example.futebolsabado.domain.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(

    private val dao: PlayerDao

) : PlayerRepository{
    override suspend fun insert(
        nome: String,
        jogos: Int,
        vitorias: Int,
        golos: Int
    ) {
        val entity = PlayerEntity(
            nome = nome,
            jogos = jogos,
            vitorias = vitorias,
            golos = golos
        )
        dao.insert(entity)
    }

    override suspend fun update(id: Int) {
        val existentingEntity = dao.getBy(id) ?: return
        val updatedEntity = existentingEntity.copy()
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Int) {
        val existentingEntity = dao.getBy(id) ?: return
        dao.delete(existentingEntity)

    }

    override fun getAll(): Flow<List<Player>> {
        return dao.getAll().map { entities ->
            entities.map {entity ->
                Player(
                    id = entity.id,
                    nome = entity.nome,
                    jogos = entity.jogos,
                    vitorias = entity.vitorias,
                    golos = entity.golos
                )
            }
        }
    }

    override suspend fun getBy(id: Int): Player? {
        return getBy(id)?.let {entity ->
            Player(
                id = entity.id,
                nome = entity.nome,
                jogos = entity.jogos,
                vitorias = entity.vitorias,
                golos = entity.golos
            )

        }
    }
}