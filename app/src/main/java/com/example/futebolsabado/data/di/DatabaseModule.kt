package com.example.futebolsabado.data.di


import android.content.Context
import androidx.room.Room
import com.example.futebolsabado.data.PlayerDao
import com.example.futebolsabado.data.PlayerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PlayerDatabase {
        return Room.databaseBuilder(
            context,
            PlayerDatabase::class.java,
            "player_db"
        ).build()
    }

    @Provides
    fun providePlayerDao(db: PlayerDatabase): PlayerDao = db.playerDao
}
