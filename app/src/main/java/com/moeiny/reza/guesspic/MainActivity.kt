package com.moeiny.reza.guesspic

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.moeiny.reza.guesspic.view.GameActivity

class MainActivity : AppCompatActivity() {

    //Widget's
    lateinit var edtName: TextView
    lateinit var txtScoretype: TextView
    lateinit var txtScore: TextView
    lateinit var imgPlay: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()


        imgPlay.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("score",0)
            intent.putExtra("index",0)
            this.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val bundle = intent.extras
        var score:String? = bundle?.getString("score")
        if(score!=null) {
            txtScore.setText(score.toString())
        } else{
            txtScore.setText("0")
        }
    }

    fun setUpView(){
        edtName = findViewById(R.id.edt_homeActivity_name)
        txtScoretype = findViewById(R.id.txt_homeActivity_scoretype)
        txtScore = findViewById(R.id.txt_homeActivity_score)
        imgPlay = findViewById(R.id.img_homeActivity_play)
    }

}
