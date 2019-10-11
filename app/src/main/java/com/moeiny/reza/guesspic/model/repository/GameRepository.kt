package com.moeiny.reza.guesspic.model.repository


import com.moeiny.reza.guesspic.model.entity.TestInfo
import com.moeiny.reza.guesspic.utils.API
import io.reactivex.Flowable

class GameRepository(private val gameApiService: GameApiService) {

    fun getTestInfo(alt:String,token:String): Flowable<TestInfo> {
        return gameApiService.getTestInfo(API.GET_TEST_URL.value,alt,token)
    }
}