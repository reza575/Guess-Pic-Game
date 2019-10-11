package com.moeiny.reza.guesspic.presenter


import com.moeiny.reza.guesspic.model.entity.TestInfo
import com.moeiny.reza.guesspic.model.repository.GameRepositoryProvider
import com.moeiny.reza.guesspic.utils.FireBaseStorageCallback
import com.moeiny.reza.guesspic.utils.okHttpClientGETBuilder
import com.moeiny.reza.guesspic.utils.retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class GameService private constructor() {

    companion object {
        private var compositeDisposable: CompositeDisposable = CompositeDisposable()

        fun getTestInfo(alt:String,token:String,block: FireBaseStorageCallback<TestInfo, Throwable>) {
            val disposableService: Disposable = GameRepositoryProvider.provideGameRepository(retrofit(okHttpClientGETBuilder()))
                .getTestInfo(alt,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                block.onSuccess(result = result)
                            },
                            {error ->
                                block.onError(error)
                            },
                            {
                                block.onComplete()
                            }
                    )

            compositeDisposable.add(disposableService)

        }

    }

}