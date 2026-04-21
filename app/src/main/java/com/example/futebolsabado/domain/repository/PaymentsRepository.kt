package com.example.futebolsabado.domain.repository

import com.example.futebolsabado.domain.model.Payments

interface PaymentRepository {
    suspend fun getPaymentsByYear(year: Int): List<Payments>
    suspend fun addPayment(playerId: Int, month: Int, year: Int)
    suspend fun removePayment(playerId: Int, month: Int, year: Int)
    suspend fun togglePayment(playerId: Int, month: Int, year: Int)
}