package com.example.futebolsabado.data.repository

import com.example.futebolsabado.data.entity.PaymentsEntity
import com.example.futebolsabado.data.local.dao.PaymentDao
import com.example.futebolsabado.domain.model.Payments
import com.example.futebolsabado.domain.repository.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val dao: PaymentDao
) : PaymentRepository {

    override suspend fun getPaymentsByYear(year: Int): List<Payments> {
        return dao.getPaymentsByYear(year).map { entity ->
            Payments(
                id = entity.id,
                playerId = entity.playerId,
                month = entity.month,
                year = entity.year
            )
        }
    }

    override suspend fun addPayment(playerId: Int, month: Int, year: Int) {
        dao.insert(
            PaymentsEntity(
                playerId = playerId,
                month = month,
                year = year
            )
        )
    }

    override suspend fun removePayment(playerId: Int, month: Int, year: Int) {
        dao.deleteByPlayerMonthYear(playerId, month, year)
    }

    override suspend fun togglePayment(playerId: Int, month: Int, year: Int) {
        val exists = dao.exists(playerId, month, year)

        if (exists) {
            dao.deleteByPlayerMonthYear(playerId, month, year)
        } else {
            dao.insert(
                PaymentsEntity(
                    playerId = playerId,
                    month = month,
                    year = year
                )
            )
        }
    }
}