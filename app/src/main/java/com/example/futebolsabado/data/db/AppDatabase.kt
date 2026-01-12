package com.example.futebolsabado.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.futebolsabado.data.dao.MatchDao
import com.example.futebolsabado.data.entity.PlayerEntity
import com.example.futebolsabado.data.dao.PlayerDao
import com.example.futebolsabado.data.entity.MatchEntity
import com.example.futebolsabado.data.entity.MatchPlayerEntity
@Database(
    entities = [
        PlayerEntity::class,
        MatchEntity::class,
        MatchPlayerEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun matchDao(): MatchDao
}



object AppDatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "players.db"
            )
                .build()
                .also { INSTANCE = it }
        }
    }
}