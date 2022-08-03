package com.example.mathsquizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.mathsquizapp.Model.MathsQuestions
import com.example.mathsquizapp.Model.QuestionAnswer
import com.example.mathsquizapp.Model.QuizDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class QuestionsPage : AppCompatActivity() {

    private var db: QuizDatabase = QuizDatabase(this)
    private var username: String? = null
    // tracks the question number
    private var questionNumber: Int = 1
    // gets the list of QuestionAnswers (later initialised)
    private var questionAnswerList: ArrayList<QuestionAnswer>? = null
    // gets the SIZE of the list of QuestionAnswers (later initialised)
    private var questionAnswerListSize: Int = 0
    // sets the maximum number of questions
    private val maximumNumberOfQuestions: Int = 14
    // tracks the score
    private var scoreCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_page)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        username = intent.getStringExtra("Username")

        // adds QuestionAnswers to the database
        db.addQuestionAnswerList(MathsQuestions.questionsAnswersList())
        // populates the 'questionAnswerList' variables by getting the list of QuestionAnswers from the database
        questionAnswerList = db.getAllQuestionAnswer()
        // assigns the number of QuestionAnswers by getting th size of the list from 'questionAnswerList'
        questionAnswerListSize = questionAnswerList!!.size
        // calls the 'setQuestions()' function which applies the layout of each QuestionAnswers
        setQuestions()
    }

    /**
     * Applies each field from QuestionAnswer to the UI
     *
     */
    private fun setQuestions() {
        /***************** UI values *******************************************/
        val topic = findViewById<TextView>(R.id.textView_Topic)
        val textViewQuestion = findViewById<TextView>(R.id.textView_Question)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar_Progress)
        val textViewProgress = findViewById<TextView>(R.id.textView_Progress)
        val button1 = findViewById<RadioButton>(R.id.button_One)
        val button2 = findViewById<RadioButton>(R.id.button_Two)
        val button3 = findViewById<RadioButton>(R.id.button_Three)
        val button4 = findViewById<RadioButton>(R.id.button_Four)
        val button5 = findViewById<RadioButton>(R.id.button_Five)
        val buttonNext = findViewById<Button>(R.id.button_Next)
        /***********************************************************************/

        val randomIndex = (0 until questionAnswerListSize!!).random()

        // gets the question at random position
        val question = questionAnswerList!![randomIndex]

        if (questionNumber == maximumNumberOfQuestions) buttonNext.text = "FINISH"

        // sets the topic
        topic.text = question.topic
        // sets the question
        textViewQuestion.text = question.question
        // sets the progress of the proressBar to 1
        progressBar.progress = questionNumber
        // sets the text of the progress
        textViewProgress.text = "$questionNumber/${progressBar.max}"
        // sets the answers by shuffling in random order
        val buttonList = arrayListOf(button1, button2, button3, button4, button5)
        buttonList.shuffle()
        buttonList[0].text = question.correctAnswer
        buttonList[1].text = question.answer2
        buttonList[2].text = question.answer3
        buttonList[3].text = question.answer4
        buttonList[4].text = question.answer5

        for (button in buttonList) {
            button.background = ContextCompat.getDrawable(this, R.drawable.radio_button_colour_bg)
        }
    }

    /**
     * Sets the 'default' appearance of the buttons
     */
    fun defaultButtonAppearance() {
        /***************** UI values *******************************************/
        val button1 = findViewById<RadioButton>(R.id.button_One)
        val button2 = findViewById<RadioButton>(R.id.button_Two)
        val button3 = findViewById<RadioButton>(R.id.button_Three)
        val button4 = findViewById<RadioButton>(R.id.button_Four)
        val button5 = findViewById<RadioButton>(R.id.button_Five)
        val buttonGroup = findViewById<RadioGroup>(R.id.radioGroup_Options)
        /***********************************************************************/

        buttonGroup.clearCheck()

        val buttonList = arrayListOf(button1, button2, button3, button4, button5)

        for (button in buttonList) {
            button.background = ContextCompat.getDrawable(this, R.drawable.radio_button_colour_bg)
            button.setTextColor(Color.BLACK)
            button.isEnabled = true
        }
    }

    /**
     * Sets the appearance of the buttons if answer is correct/incorrect
     */
    fun correctButtonAppearance(buttonGroup: RadioGroup) {
        /***************** UI values *******************************************/
        val textViewQuestion = findViewById<TextView>(R.id.textView_Question)
        val button1 = findViewById<RadioButton>(R.id.button_One)
        val button2 = findViewById<RadioButton>(R.id.button_Two)
        val button3 = findViewById<RadioButton>(R.id.button_Three)
        val button4 = findViewById<RadioButton>(R.id.button_Four)
        val button5 = findViewById<RadioButton>(R.id.button_Five)
        /***********************************************************************/

        val checkedButtonID = buttonGroup.checkedRadioButtonId
        val selectedButton = findViewById<RadioButton>(checkedButtonID)
        val correctAnswer = db.getCorrectAnswerFromQuestion(textViewQuestion.text.toString())

        val buttonList = arrayListOf(button1, button2, button3, button4, button5)

        if (selectedButton.text == correctAnswer) {
            selectedButton.background = ContextCompat.getDrawable(this,
                R.drawable.selected_correct_answer_radio_button_colour_bg)
            for (button in buttonList) {
                if (!button.isChecked) {
                    button.setTextColor(Color.GRAY)
                    button.isEnabled = false
                }
            }
        } else if (selectedButton.text != correctAnswer) {
            selectedButton.background = ContextCompat.getDrawable(this,
                R.drawable.selected_wrong_answer_radio_button_colour_bg)
            for (button in buttonList) {
                if (!button.isChecked) {
                    button.setTextColor(Color.GRAY)
                    button.isEnabled = false
                    if (button.text == correctAnswer) {
                        button.background = ContextCompat.getDrawable(this, R.drawable.correct_answer_radio_button_colour_bg)
                    }
                }
            }
        }
    }


    /**
     * Defines the functions of the Submit button
     *
     */
    fun confirmAnswer(view: View) {
        /***************** UI values *******************************************/
        val textViewQuestion = findViewById<TextView>(R.id.textView_Question)
        val buttonGroup = findViewById<RadioGroup>(R.id.radioGroup_Options)
        val button1 = findViewById<RadioButton>(R.id.button_One)
        val button2 = findViewById<RadioButton>(R.id.button_Two)
        val button3 = findViewById<RadioButton>(R.id.button_Three)
        val button4 = findViewById<RadioButton>(R.id.button_Four)
        val button5 = findViewById<RadioButton>(R.id.button_Five)
        val buttonNext = findViewById<Button>(R.id.button_Next)
        val buttonConfirmAnswer = findViewById<Button>(R.id.button_ConfirmAnswer)
        /***********************************************************************/

        val checkedButtonID = findViewById<RadioGroup>(R.id.radioGroup_Options).checkedRadioButtonId
        val selectedButton = findViewById<RadioButton>(checkedButtonID)
        val correctAnswer = db.getCorrectAnswerFromQuestion(textViewQuestion.text.toString())

        var buttonList = arrayListOf<RadioButton>(button1, button2, button3, button4, button5)

        // if no buttons are selected....
        if (selectedButton == null) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show()
        // else if buttons are selected....
        } else {
            // if CORRECT answer is selected....
            if (selectedButton.text == correctAnswer) {
                scoreCount++
                // change appearance of each button....
                correctButtonAppearance(buttonGroup)
            // else if INCORRECT answer is selected....
            } else if (selectedButton.text != correctAnswer) {
                // change appearance of each button....
                correctButtonAppearance(buttonGroup)
            }
            buttonConfirmAnswer.isVisible = false
            buttonNext.isVisible = true
        }
    }

    fun next(view: View) {
        val buttonConfirmAnswer = findViewById<Button>(R.id.button_ConfirmAnswer)
        val buttonNext = findViewById<Button>(R.id.button_Next)

        if (questionNumber == maximumNumberOfQuestions) {
            val intent = Intent (this, ResultsPage::class.java).apply {
                putExtra("Username", username)
                putExtra("Score", scoreCount)
                putExtra("NumberOfQuestions", maximumNumberOfQuestions)
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.format(java.util.Date())
            db.addScore(username!!, scoreCount, date)
            startActivity(intent)
        } else {
            // increases questionNumber
            questionNumber++
            // sets random question
            setQuestions()
            // sets default appearance
            defaultButtonAppearance()

            buttonNext.isVisible = false
            buttonConfirmAnswer.isVisible = true
        }
    }

}


