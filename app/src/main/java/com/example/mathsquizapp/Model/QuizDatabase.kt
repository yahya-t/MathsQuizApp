package com.example.mathsquizapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.lang.IllegalArgumentException

private val DatabaseName = "MathsQuizDatabase.db"
private val version: Int = 1

class QuizDatabase(context: Context) : SQLiteOpenHelper(context, DatabaseName, null, version) {

    /******* Table_User *********/
    private val Table_User = "Table_User"
    private val Column_UserID = "UserID"
    private val Column_Username = "Username"
    private val Column_Password = "Password"
    private val Column_Score = "Score"
    private val Column_Date = "Date"

    /******* Table_Admin *********/
    private val Table_Admin = "Table_Admin"
    private val Column_AdminID = "AdminID"
    private val Column_Admin_Username = "AdminUsername"
    private val Column_Admin_Password = "Password"

    /******* Question_Answer Table *********/
    private val Table_QuestionsAnswers = "Table_QuestionsAnswers"
    private val Column_QuestionID = "QuestionID"
    private val Column_Topic = "Topic"
    private val Column_Question = "Question"
    private val Column_Correct_Answer = "CorrectAnswer"
    private val Column_Answer2 = "Answer2"
    private val Column_Answer3 = "Answer3"
    private val Column_Answer4 = "Answer4"
    private val Column_Answer5 = "Answer5"


    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlStatement = "CREATE TABLE " + Table_User + " ( " +
                    Column_UserID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Column_Username + " TEXT NOT NULL, " +
                    Column_Password + " TEXT NOT NULL, " +
                    Column_Score + " INTEGER, " +
                    Column_Date + " TEXT ) "

            db?.execSQL(sqlStatement)

            sqlStatement = "CREATE TABLE " + Table_Admin + " ( " +
                    Column_AdminID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Column_Admin_Username + " TEXT NOT NULL, " +
                    Column_Admin_Password + " TEXT NOT NULL ) "
            db?.execSQL(sqlStatement)

            sqlStatement = "CREATE TABLE " + Table_QuestionsAnswers + " ( " +
                    Column_QuestionID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Column_Topic + " TEXT NOT NULL, " +
                    Column_Question + " TEXT TEXT NOT NULL, " +
                    Column_Correct_Answer + " TEXT NOT NULL, " +
                    Column_Answer2 + " TEXT NOT NULL, " +
                    Column_Answer3 + " TEXT NOT NULL, " +
                    Column_Answer4 + " TEXT NOT NULL, " +
                    Column_Answer5 + " TEXT NOT NULL ) "
                    db?.execSQL(sqlStatement)

        } catch (e: SQLException) {
            e.message
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /************************************************
     * ********************************************
     *              User Table
     * ********************************************
     ***********************************************/

    /**
     * Checks if a user already exists
     */
    fun checkUserExists(username: String): Boolean {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT  * FROM $Table_User WHERE $Column_Username = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // user is found
            cursor.close()
            db.close()
            return true
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return false
        }
    }

    /**
     * Adds a user
     */
    fun addUser(username: String, password: String) {
        val doesUsernameExist = checkUserExists(username)

        if (!doesUsernameExist) {
            // 'writeableDatabase' method used on this database to allow it to be edited
            val db: SQLiteDatabase = this.writableDatabase
            // ContentValues stores the values which are to be used for the database
            val cv: ContentValues = ContentValues()
            cv.put(Column_Username, username)
            cv.put(Column_Password, password)
            // inserts the values from 'cv' (ContentValues()) into the database
            val success = db.insert(Table_User, null, cv)
            db.close()
        }
    }

    /**
     * Gets the user
     */
    fun getUser(username: String, password: String): Boolean {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement =
            "SELECT * FROM $Table_User WHERE $Column_Username = ? AND $Column_Password = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username, password)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // user is found
            cursor.close()
            db.close()
            return true
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return false
        }
    }

    /**
     * Adds the score to the associated username
     */
    fun addScore(username: String, score: Int, date: String) {
        val doesUsernameExist = checkUserExists(username)

        if (!doesUsernameExist) {
            // 'writeableDatabase' method used on this database to allow it to be edited
            val db: SQLiteDatabase = this.writableDatabase
            var currentScore: Int? = null
            var currentDate: String? = null

            // assign current score to previous score
            // ContentValues stores the values which are to be used for the database
            val cv: ContentValues = ContentValues()
            cv.put(Column_Score, score)
            cv.put(Column_Date, date)

            // inserts the values from 'cv' (ContentValues()) into the database
            val success = db.insert(Table_User, null, cv)
            db.close()
        }
    }

    /**
     * Returns previous score and new score of the user
     */
    fun getScore(username: String): Int {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT $Column_Score FROM $Table_User WHERE $Column_Username = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        var score: Int? = null

        if (cursor.moveToFirst()) {
            // user is found
            score = cursor.getInt(0)
            cursor.close()
            db.close()
            return score
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return 0
        }
    }

    /**
     * Returns the current score and date of the user
     */
    fun getScoreAndDate(username: String): ArrayList<String> {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT $Column_Score, $Column_Date FROM $Table_User WHERE $Column_Username = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        val scoreAndDate = ArrayList<String>()

        if (cursor.moveToFirst()) {
            // user is found
            scoreAndDate.add(cursor.getInt(0).toString())
            scoreAndDate.add(cursor.getString(1))
            cursor.close()
            db.close()
            return scoreAndDate
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return scoreAndDate
        }
    }

    /************************************************
     * ********************************************
     *              Admin Table
     * ********************************************
     ***********************************************/

    /**
     * Checks if admin already exists
     */
    fun checkAdminExists(username: String): Boolean {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT  * FROM $Table_Admin WHERE $Column_Admin_Username = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // user is found
            cursor.close()
            db.close()
            return true
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return false
        }
    }

    /**
     * Adds admin
     */
    fun addAdmin(username: String, password: String) {
        val doesUsernameExist = checkUserExists(username)

        if (!doesUsernameExist) {
            // 'writeableDatabase' method used on this database to allow it to be edited
            val db: SQLiteDatabase = this.writableDatabase
            // ContentValues stores the values which are to be used for the database
            val cv: ContentValues = ContentValues()
            cv.put(Column_Admin_Username, username)
            cv.put(Column_Admin_Password, password)
            // inserts the values from 'cv' (ContentValues()) into the database
            val success = db.insert(Table_Admin, null, cv)
            db.close()
        }
    }

    /**
     * Gets the admin
     */
    fun getAdmin(username: String, password: String): Boolean {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement =
            "SELECT * FROM $Table_Admin WHERE $Column_Admin_Username = ? AND $Column_Admin_Password = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(username, password)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // user is found
            cursor.close()
            db.close()
            return true
        } else {
            cursor.close()
            db.close()
            // user NOT found
            return false
        }
    }

    /************************************************
     * ********************************************
     *              Questions Table
     * ********************************************
     ***********************************************/

    /**
     * Adds a QuestionAnswer object
     */
    fun addQuestionAnswer(questionAnswer: QuestionAnswer) {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        try {
            // ContentValues stores the values which are to be used for the database
            val cv: ContentValues = ContentValues()
            cv.put(Column_Topic, questionAnswer.topic)
            cv.put(Column_Question, questionAnswer.question)
            cv.put(Column_Correct_Answer, questionAnswer.correctAnswer)
            cv.put(Column_Answer2, questionAnswer.answer2)
            cv.put(Column_Answer3, questionAnswer.answer3)
            cv.put(Column_Answer4, questionAnswer.answer4)
            cv.put(Column_Answer5, questionAnswer.answer5)
            // inserts the values from 'cv' (ContentValues()) into the database
            val success = db.insert(Table_QuestionsAnswers, null, cv)
            db.close()
        } catch (e: SQLiteException) {
            throw SQLiteException("Error adding Question and Answers....")
        }
    }

    /**
     * Adds an ArrayList containing QuestionAnswer objects
     */
    fun addQuestionAnswerList(questionAnswerList: ArrayList<QuestionAnswer>) {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.writableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        for (questionAnswer in questionAnswerList) {
            try {
                // ContentValues stores the values which are to be used for the database
                val cv: ContentValues = ContentValues()
                cv.put(Column_Topic, questionAnswer.topic)
                cv.put(Column_Question, questionAnswer.question)
                cv.put(Column_Correct_Answer, questionAnswer.correctAnswer)
                cv.put(Column_Answer2, questionAnswer.answer2)
                cv.put(Column_Answer3, questionAnswer.answer3)
                cv.put(Column_Answer4, questionAnswer.answer4)
                cv.put(Column_Answer5, questionAnswer.answer5)
                // inserts the values from 'cv' (ContentValues()) into the database
                val success = db.insert(Table_QuestionsAnswers, null, cv)
            } catch (e: SQLiteException) {
                throw SQLiteException("Error adding Question and Answers....")
            }
        }
        db.close()
    }

    /**
     * Returns the QuestionAnswer object from the 'question' provided in the parameter
     */
    fun getQuestionAnswer(question: String): QuestionAnswer {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT * FROM $Table_QuestionsAnswers WHERE $Column_Question = ?"
        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(question)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // QuestionAnswer is found
            cursor.close()
            db.close()
            return QuestionAnswer(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getString(4),
                                    cursor.getString(5),
                                    cursor.getString(6))
        } else {
            cursor.close()
            db.close()
            // QuestionAnswer NOT found
            return QuestionAnswer("", "", "", "", "", "", "")
        }
    }

    /**
     * Returns the ArrayList of QuestionAnswer objects
     */
    fun getAllQuestionAnswer(): ArrayList<QuestionAnswer> {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        val sqlStatement = "SELECT * FROM $Table_QuestionsAnswers"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        val questionAnswerList = ArrayList<QuestionAnswer>()

        // if QuestionAnswer is found
        if (cursor.moveToFirst()) {
            // do-while loop to add all QuestionAnswers
            do {
                val topic = cursor.getString(1)
                val question = cursor.getString(2)
                val correctAnswer = cursor.getString(3)
                val answer2 = cursor.getString(4)
                val answer3 = cursor.getString(5)
                val answer4 = cursor.getString(6)
                val answer5 = cursor.getString(7)

                val questionAnswer = QuestionAnswer(topic, question, correctAnswer, answer2, answer3, answer4, answer5)
                questionAnswerList.add(questionAnswer)
            } while (cursor.moveToNext())
        }
        return questionAnswerList
    }

    /**
     * Returns the 'answer' from the 'questions' provided in the parameter
     */
    fun getAnswerFromQuestion(question: String): ArrayList<String> {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT " + Column_Correct_Answer + ", " +
                                        Column_Answer2 + ", " +
                                        Column_Answer3 + ", " +
                                        Column_Answer4 + ", " +
                                        Column_Answer5 +
                                        " FROM " + Table_QuestionsAnswers + " WHERE " + Column_Question + " = ?"

        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(question)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        // ArrayList to store answers
        val answers = ArrayList<String>()

        if (cursor.moveToFirst()) {
            // answers found
            answers.add(cursor.getString(0))
            answers.add(cursor.getString(1))
            answers.add(cursor.getString(2))
            answers.add(cursor.getString(3))
            answers.add(cursor.getString(4))
            cursor.close()
            db.close()
            return answers
        } else {
            cursor.close()
            db.close()
            // answers NOT found
            answers.clear()
            return answers
        }
    }

    /**
     * Returns the 'correct answer' from the question provided in the parameter
     */
    fun getCorrectAnswerFromQuestion(question: String): String {
        /* try connecting to database */
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            throw SQLException("Cannot connect to database!")
        }

        // the ? is the parameter that will be later replace
        val sqlStatement = "SELECT $Column_Correct_Answer FROM $Table_QuestionsAnswers WHERE $Column_Question = ?"

        // this is the parameter to replace the ? in the above sqlStatement
        val param = arrayOf(question)
        // cursor object takes a 'rawQuery' and executes the sqlStatement and uses the param as the parameter for the ?
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        // ArrayList to store answers
        var correctAnswer: String = ""

        if (cursor.moveToFirst()) {
            // correctAnswer found
            correctAnswer = cursor.getString(0)
            cursor.close()
            db.close()
            return correctAnswer
        } else {
            cursor.close()
            db.close()
            // correctAnswer NOT found
            return correctAnswer
        }
    }

}