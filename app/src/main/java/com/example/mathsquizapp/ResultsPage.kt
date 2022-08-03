package com.example.mathsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import com.example.mathsquizapp.Model.QuizDatabase
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant

class ResultsPage : AppCompatActivity() {

    val db = QuizDatabase(this)
    var username: String? = null
    var score: Int? = null
    var numberOfQuestions: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_page)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        // get values from Intent
        username = intent.getStringExtra("Username")
        score = intent.getIntExtra("Score", 0)
        numberOfQuestions = intent.getIntExtra("NumberOfQuestions", 0)

        // set the messages
        findViewById<TextView>(R.id.textView_Username).text = username
        findViewById<TextView>(R.id.textView_Score).text = "Your score is $score out of $numberOfQuestions !"

        addImage()
    }

    fun addImage() {
        val score = score
        val image = findViewById<ImageView>(R.id.imageView_Image)

        if (score != null) {
            if (score == numberOfQuestions) {
                image.setImageResource(R.drawable.img_gold_trophy)
            } else {
                image.setImageResource(R.drawable.img_silver_trophy)
            }
        }
    }

    fun playAgain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}