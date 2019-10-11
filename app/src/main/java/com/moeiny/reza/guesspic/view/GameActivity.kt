package com.moeiny.reza.guesspic.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moeiny.reza.guesspic.R
import com.moeiny.reza.guesspic.utils.SharedPreference
import com.moeiny.reza.guesspic.model.entity.TestInfo
import com.moeiny.reza.guesspic.presenter.GameService
import com.moeiny.reza.guesspic.utils.FireBaseStorageCallback
import com.squareup.picasso.Picasso

class GameActivity : AppCompatActivity() {

    //Widget's
    lateinit var txtScore: TextView
    lateinit var txtH0: TextView
    lateinit var txtH1: TextView
    lateinit var txtH2: TextView
    lateinit var txtSkip: TextView
    lateinit var imgPicUrl: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var sharedPreference: SharedPreference

    //Var's
    lateinit var context :Context
    lateinit var info: TestInfo
    var correctIndex: Int = 0
    var win: Int = 0
    var score: Int = 0
    var index: Int = 0
    var resultsize: Int = 0
    var standFirst: String = ""
    var storyUrl: String = ""
    var section: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        context=this
        val bundle = intent.extras
        var index:Int? = bundle?.getInt("index")
        var score:Int? = bundle?.getInt("score")
        sharedPreference = SharedPreference(context!!)
        sharedPreference.save("index", index!!)
        sharedPreference.save("score",score!!)

        setUpView()

        getTestInfo()   //Give Data from server

    }

    override fun onResume() {
        super.onResume()
        index=sharedPreference.getValueInt("index")
        if (index>0){
            setUpData()
        }
    }


    fun getTestInfo() {

        var alt: String = "media"
        var token: String = "e36c1a14-25d9-4467-8383-a53f57ba6bfe"

        GameService.getTestInfo(alt, token, object : FireBaseStorageCallback<TestInfo, Throwable> {

            override fun onSuccess(result: TestInfo) {
                info = result

                setUpData()    //Puting the Data to Widget and set the Var's
            }

            override fun onError(error: Throwable?) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                print("complete")
            }

        })
    }

    fun setUpData() {
        resultsize=info.resultSize
        Picasso.get().load(info.items[index].imageUrl).into(imgPicUrl)
        txtH0.setText(info.items[index].headlines[0])
        txtH1.setText(info.items[index].headlines[1])
        txtH2.setText(info.items[index].headlines[2])
        correctIndex = info.items[index].correctAnswerIndex
        standFirst = info.items[index].standFirst
        storyUrl = info.items[index].storyUrl
        section = info.items[index].section
        sharedPreference.save("picUrl", info.items[index].imageUrl)
        txtScore.setText(sharedPreference.getValueInt("score").toString())
        progressBar.progress=sharedPreference.getValueInt("score")
    }


    fun setUpView() {
        txtScore = findViewById(R.id.txt_gameFragment_score)
        txtH0 = findViewById(R.id.txt_gameFragment_h0)
        txtH1 = findViewById(R.id.txt_gameFragment_h1)
        txtH2 = findViewById(R.id.txt_gameFragment_h2)
        txtSkip = findViewById(R.id.txt_gameFragment_skip)
        imgPicUrl = findViewById(R.id.img_fragmentGame_picurl)
        progressBar = findViewById(R.id.pb_gameFragment_score)
        progressBar.max=100

        //Choosing First Headline
        txtH0.setOnClickListener {
            if (txtH0.tag == correctIndex.toString()) {
                score=sharedPreference.getValueInt("score")
                score=score+2
                sharedPreference.save("score",score)
                win=1
            }
            else{
                score=sharedPreference.getValueInt("score")
                score=score-1
                sharedPreference.save("score",score)
                win=0
            }

            index=sharedPreference.getValueInt("index")
            index=index+1
            sharedPreference.save("index",index)

            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("Answer", info.items[index-1].headlines[correctIndex])
            intent.putExtra("picUrl", info.items[index-1].imageUrl)
            intent.putExtra("storyUrl", info.items[index-1].storyUrl)
            intent.putExtra("score", score.toString())
            intent.putExtra("win", win.toString())
            context!!.startActivity(intent)   //Send data to DetailActivity
        }

        //Choosing Secone Headline
        txtH1.setOnClickListener {
            if (txtH1.tag == correctIndex.toString()) {
                score=sharedPreference.getValueInt("score")
                score=score+2
                sharedPreference.save("score",score)
                win=1
            }
            else{
                score=sharedPreference.getValueInt("score")
                score=score-1
                sharedPreference.save("score",score)
                win=0
            }

            index=sharedPreference.getValueInt("index")
            index=index+1
            sharedPreference.save("index",index)


            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("Answer", info.items[index-1].headlines[correctIndex])
            intent.putExtra("picUrl", info.items[index-1].imageUrl)
            intent.putExtra("storyUrl", info.items[index-1].storyUrl)
            intent.putExtra("score", score.toString())
            intent.putExtra("win", win.toString())
            context!!.startActivity(intent)   //Send data to DetailActivity
        }

        //Choosing Third Headline
        txtH2.setOnClickListener {
            if (txtH2.tag == correctIndex.toString()) {
                score=sharedPreference.getValueInt("score")
                score=score+2
                sharedPreference.save("score",score)
                win=1
            }
            else{
                score=sharedPreference.getValueInt("score")
                score=score-1
                sharedPreference.save("score",score)
                win=0
            }

            index=sharedPreference.getValueInt("index")
            index=index+1
            sharedPreference.save("index",index)


            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("Answer", info.items[index-1].headlines[correctIndex])
            intent.putExtra("picUrl", info.items[index-1].imageUrl)
            intent.putExtra("storyUrl", info.items[index-1].storyUrl)
            intent.putExtra("score", score.toString())
            intent.putExtra("win", win.toString())
            context!!.startActivity(intent)   //Send data to DetailActivity
        }


        txtSkip.setOnClickListener{
            index=sharedPreference.getValueInt("index")
            index=index+1;
            sharedPreference.save("index",index)
            setUpData()
        }
    }
}
