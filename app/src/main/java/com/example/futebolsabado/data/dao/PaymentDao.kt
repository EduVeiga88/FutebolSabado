package com.example.futebolsabado.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.futebolsabado.data.entity.PaymentsEntity


@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(payment: PaymentsEntity)

    @Query("""
        DELETE FROM payments
        WHERE playerId = :playerId AND month = :month AND year = :year
    """)
    suspend fun deleteByPlayerMonthYear(
        playerId: Int,
        month: Int,
        year: Int
    )

    @Query("""
        SELECT * FROM payments
        WHERE year = :year
        ORDER BY playerId ASC, month ASC
    """)
    suspend fun getPaymentsByYear(year: Int): List<PaymentsEntity>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM payments
            WHERE playerId = :playerId AND month = :month AND year = :year
        )
    """)
    suspend fun exists(
        playerId: Int,
        month: Int,
        year: Int
    ): Boolean
}