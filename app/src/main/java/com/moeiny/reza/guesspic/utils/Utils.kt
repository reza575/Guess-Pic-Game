package com.moeiny.reza.guesspic.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


enum class API(val value: String) {
    KEY(""),
    TIMEOUT_IN_MS("30000"),
    BASE_URL("https://firebasestorage.googleapis.com"),
    GET_TEST_URL("/v0/b/nca-dna-apps-dev.appspot.com/o/game.json"),

}

fun okHttpClientGETBuilder() : OkHttpClient {

    val clientBuilder = OkHttpClient.Builder()

   // Create a new Interceptor.
    val headerAuthorizationInterceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            request = request.newBuilder().build()
            return chain.proceed(request)
        }
    }

    //Add the interceptor to the client builder.
    clientBuilder.addNetworkInterceptor(headerAuthorizationInterceptor)
    return clientBuilder.build()
}

fun retrofit(httpClient: OkHttpClient): Retrofit {
    val retrofit = Retrofit.Builder()
            .baseUrl(API.BASE_URL.value)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    return retrofit
}

open interface FireBaseStorageCallback<in T, in R> {
    fun onSuccess(result: T)
    fun onError(error: R?)
    fun onComplete()
}