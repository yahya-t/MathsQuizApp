package com.example.mathsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mathsquizapp.Model.QuizDatabase

class CreateNewProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_profile_page)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }

    fun confirm(view: View) {
        val db: QuizDatabase = QuizDatabase(this)
        val username = findViewById<EditText>(R.id.editText_CreatUsername).text.toString()
        val password = findViewById<EditText>(R.id.editText_CreatePassword).text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (username.length < 4) {
            Toast.makeText(this, "Username must be more than 4 characters!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (password.length < 4 ||
            !(password.any { it.isDigit() }) ){
            Toast.makeText(this, "Password must be at least 4 characters and have a number!", Toast.LENGTH_SHORT).show()
        } else {
            if (db.checkUserExists(username) == false && db.getUser(username, password) == false) {
                db.addUser(username, password)
                Toast.makeText(this, "Successfully created your new profile $username!", Toast.LENGTH_SHORT).show()
            } else if (db.checkUserExists(username) == true || db.getUser(username, password) == true) {
                Toast.makeText(this, "Account already exists! Please click Return To Login to login to this profile", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun returnToLoginPage(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}