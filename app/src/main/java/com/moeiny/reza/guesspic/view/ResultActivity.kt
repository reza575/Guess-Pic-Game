package com.moeiny.reza.guesspic.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.moeiny.reza.guesspic.MainActivity
import com.moeiny.reza.guesspic.R
import com.squareup.picasso.Picasso

class ResultActivity : AppCompatActivity() {

    //Widget's
    lateinit var txtAnswer: TextView
    lateinit var txtScore: TextView
    lateinit var txtCircle: TextView
    lateinit var txtLink: TextView
    lateinit var txtNext: TextView
    lateinit var txtExit: TextView
    lateinit var imgPicUrl: ImageView

    //Var's
    var answer:String?=""
    var picUrl:String?=""
    var storyUrl:String?=""
    var score:String?=""
    var win:String?=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bundle = intent.extras
        answer = bundle?.getString("Answer")
        picUrl = bundle?.getString("picUrl")
        storyUrl = bundle?.getString("storyUrl")
        score = bundle?.getString("score")
        win = bundle?.getString("win")

        setUpView()

        txtLink.setOnClickListener {
            val url = storyUrl
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


        txtNext.setOnClickListener {
            finish()
        }


        txtExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("score",score)
            this.startActivity(intent)
        }
    }


    fun setUpView(){
        txtAnswer = findViewById(R.id.txt_resultActivity_answer)
        txtScore = findViewById(R.id.txt_resultActivity_score)
        txtCircle = findViewById(R.id.txt_resultActivity_circle)
        txtLink = findViewById(R.id.txt_resultActivity_link)
        txtNext = findViewById(R.id.txt_resultActivity_next)
        txtExit = findViewById(R.id.txt_resultActivity_exit)
        imgPicUrl = findViewById(R.id.img_resultActivity_picUrl)

        Picasso.get().load(picUrl).into(imgPicUrl)
        txtAnswer.setText(answer)
        txtScore.setText(score)

        if(win.equals("1")){
            txtCircle.setBackgroundResource(R.drawable.goodluck)
        } else{
            txtCircle.setBackgroundResource(R.drawable.badluck)
        }
    }

}
