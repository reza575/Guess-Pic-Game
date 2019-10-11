package com.moeiny.reza.guesspic.presenter


import com.moeiny.reza.guesspic.model.entity.TestInfo
import com.moeiny.reza.guesspic.utils.FireBaseStorageCallback

interface IGameService {

    fun getTestInfo(alt:String,token:String,block: FireBaseStorageCallback<TestInfo, Throwable>)

}