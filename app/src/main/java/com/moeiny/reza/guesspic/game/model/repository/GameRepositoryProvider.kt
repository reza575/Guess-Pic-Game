package com.moeiny.reza.guesspic.game.model.repository

import com.moeiny.reza.guesspic.game.model.repository.GameRepository
import com.moeiny.reza.guesspic.game.model.repository.GameApiService
import retrofit2.Retrofit

object GameRepositoryProvider {

    fun provideGameRepository(retrofit: Retrofit) : GameRepository {
        return GameRepository(GameApiService.create(retrofit))
    }
}