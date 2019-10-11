package com.moeiny.reza.guesspic.model.repository

import retrofit2.Retrofit

object GameRepositoryProvider {

    fun provideGameRepository(retrofit: Retrofit) : GameRepository {
        return GameRepository(GameApiService.create(retrofit))
    }
}