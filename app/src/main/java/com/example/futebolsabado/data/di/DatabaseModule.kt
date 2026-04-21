package com.example.futebolsabado.data.di


import android.content.Context
import androidx.room.Room
import com.example.futebolsabado.data.dao.MatchDao
import com.example.futebolsabado.data.dao.PlayerDao
import com.example.futebolsabado.data.db.AppDatabase
import com.example.futebolsabado.data.local.dao.PaymentDao
import com.example.futebolsabado.data.repository.PaymentRepositoryImpl
import com.example.futebolsabado.domain.repository.PaymentRepository
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
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "player_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePlayerDao(db: AppDatabase): PlayerDao =
        db.playerDao()

    @Provides
    fun provideMatchDao(db: AppDatabase): MatchDao =
        db.matchDao()
    @Provides
    fun providePaymentDao(database: AppDatabase): PaymentDao {
        return database.paymentDao()
    }

    @Provides
    fun providePaymentRepository(dao: PaymentDao): PaymentRepository {
        return PaymentRepositoryImpl(dao)
    }
}

