package com.example.futebolsabado.data.di


import com.example.futebolsabado.data.repository.MatchRepositoryImpl
import com.example.futebolsabado.domain.repository.PlayerRepository
import com.example.futebolsabado.data.repository.PlayerRepositoryImpl
import com.example.futebolsabado.domain.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlayerRepository(
        impl: PlayerRepositoryImpl
    ): PlayerRepository

    @Binds
    @Singleton
    abstract fun bindMatchRepository(
        impl: MatchRepositoryImpl
    ): MatchRepository
}
