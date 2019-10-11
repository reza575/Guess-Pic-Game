package com.moeiny.reza.guesspic.model.repository

import com.moeiny.reza.guesspic.model.entity.TestInfo
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.*

interface GameApiService {

    @GET
    fun getTestInfo(@Url url: String,@Query ("alt") alt:String,@Query("token") token:String): Flowable<TestInfo>


    companion object Factory {
        fun create(retrofit: Retrofit): GameApiService {
            return retrofit.create(GameApiService::class.java)
        }
    }
}