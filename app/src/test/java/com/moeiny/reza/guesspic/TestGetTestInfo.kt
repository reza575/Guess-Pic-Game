package com.moeiny.reza.guesspic

import com.moeiny.reza.guesspic.presenter.GameService
import com.moeiny.reza.guesspic.model.entity.TestInfo
import com.moeiny.reza.guesspic.utils.FireBaseStorageCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetTestInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetTest() {

        val lock =  CountDownLatch(1)
        lateinit var test: TestInfo
        var alt:String="media"
        var token:String="e36c1a14-25d9-4467-8383-a53f57ba6bfe"


        GameService.getTestInfo(alt,token,object : FireBaseStorageCallback<TestInfo, Throwable> {

            override fun onSuccess(result: TestInfo) {
                Assert.assertNotNull(result)
                test = result
                lock.countDown()
            }

            override fun onError(error: Throwable?) {
                Assert.assertNotNull(error)
                lock.countDown()
            }

            override fun onComplete() {
                print("complete")
                lock.countDown()
            }
        })

        test.let {
            Assert.assertEquals(it.product, "Daily Buzz")
            Assert.assertEquals(it.resultSize, 615)
            Assert.assertEquals(it.items[0].correctAnswerIndex, 2)
            Assert.assertEquals(it.items[0].imageUrl, "http://news-innovation.s3.amazonaws.com/matcha/20150105/d6a7bac9beaefea3120a8fee51eaca29.jpg")
        }
    }
}