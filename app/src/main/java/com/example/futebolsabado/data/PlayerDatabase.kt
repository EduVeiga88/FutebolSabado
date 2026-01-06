package com.example.futebolsabado.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
@Database(
    entities = [PlayerEntity::class],
    version = 1,
)

abstract class PlayerDatabase : RoomDatabase() {

    abstract val playerDao: PlayerDao

}


object PlayerDatabaseProvider {

    @Volatile
    private var INSTANCE: PlayerDatabase? = null

    fun get(context: Context): PlayerDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                PlayerDatabase::class.java,
                "players.db"
            )
                .build()
                .also { INSTANCE = it }
        }
    }
}