package com.example.futebolsabado.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.futebolsabado.data.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(entity: PlayerEntity)

    @Delete
    suspend fun delete(entity: PlayerEntity)

    @Query("SELECT * FROM player")
    fun getAll(): Flow<List<PlayerEntity>>

    @Query("SELECT *  FROM player WHERE id = :id")
    suspend fun getBy(id: Int): PlayerEntity?
}