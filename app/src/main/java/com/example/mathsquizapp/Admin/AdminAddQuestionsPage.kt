package com.example.mathsquizapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mathsquizapp.Model.QuestionAnswer
import com.example.mathsquizapp.Model.QuizDatabase
import com.example.mathsquizapp.R

class AdminAddQuestionsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_questions_page)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }

    fun addQuestions(view: View) {
        val db: QuizDatabase = QuizDatabase(this)

        val topic = findViewById<EditText>(R.id.editText_Topic).text.toString()
        val question = findViewById<EditText>(R.id.editText_Question).text.toString()
        val correctAnswer = findViewById<EditText>(R.id.editText_CorrectAnswer).text.toString()
        val answer2 = findViewById<EditText>(R.id.editText_Answer2).text.toString()
        val answer3 = findViewById<EditText>(R.id.editText_Answer3).text.toString()
        val answer4 = findViewById<EditText>(R.id.editText_Answer4).text.toString()
        val answer5 = findViewById<EditText>(R.id.editText_Answer5).text.toString()

        val questionAnswer = QuestionAnswer(topic, question, correctAnswer, answer2, answer3, answer4, answer5)

        if (topic == "" || topic.equals(null)) {
            Toast.makeText(this, "Topic is empty!", Toast.LENGTH_SHORT).show()
        } else if (question == "" || question.equals(null)) {
            Toast.makeText(this, "Question is empty!", Toast.LENGTH_SHORT).show()
        } else if (correctAnswer == "" || correctAnswer.equals(null)) {
            Toast.makeText(this, "Correct answer is empty!", Toast.LENGTH_SHORT).show()
        } else if (answer2 == "" || answer2.equals(null)) {
            Toast.makeText(this, "Incorrect answer 1 is empty!", Toast.LENGTH_SHORT).show()
        } else if (answer3 == "" || answer3.equals(null)) {
            Toast.makeText(this, "Incorrect answer 2 is empty!", Toast.LENGTH_SHORT).show()
        } else if (answer4 == "" || answer4.equals(null)) {
            Toast.makeText(this, "Incorrect answer 3 is empty!", Toast.LENGTH_SHORT).show()
        } else if (answer5 == "" || answer5.equals(null)) {
            Toast.makeText(this, "Incorrect answer 4 is empty!", Toast.LENGTH_SHORT).show()
        } else {
            db.addQuestionAnswer(questionAnswer)
            Toast.makeText(this, "Question and Answers added successfully!", Toast.LENGTH_LONG).show()
            findViewById<EditText>(R.id.editText_Topic).text.clear()
            findViewById<EditText>(R.id.editText_Question).text.clear()
            findViewById<EditText>(R.id.editText_CorrectAnswer).text.clear()
            findViewById<EditText>(R.id.editText_Answer2).text.clear()
            findViewById<EditText>(R.id.editText_Answer3).text.clear()
            findViewById<EditText>(R.id.editText_Answer4).text.clear()
            findViewById<EditText>(R.id.editText_Answer5).text.clear()
        }
    }

    fun clearFields(view: View) {
        findViewById<EditText>(R.id.editText_Topic).text.clear()
        findViewById<EditText>(R.id.editText_Question).text.clear()
        findViewById<EditText>(R.id.editText_CorrectAnswer).text.clear()
        findViewById<EditText>(R.id.editText_Answer2).text.clear()
        findViewById<EditText>(R.id.editText_Answer3).text.clear()
        findViewById<EditText>(R.id.editText_Answer4).text.clear()
        findViewById<EditText>(R.id.editText_Answer5).text.clear()
    }


}