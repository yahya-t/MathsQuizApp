package com.example.mathsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mathsquizapp.Admin.AdminLoginPage
import com.example.mathsquizapp.Model.QuizDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }

    fun login(view: View) {
        val db: QuizDatabase = QuizDatabase(this)
        val username = findViewById<EditText>(R.id.editText_Username).text.toString()
        val password = findViewById<EditText>(R.id.editText_Password).text.toString()

        if (username == "" || username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
        } else {
            if (db.getUser(username, password) == true) {
                Toast.makeText(this, "Welcome back $username!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuestionsPage::class.java).apply {
                    putExtra("Username", username)
                }
                startActivity(intent)
            } else if(db.getUser(username, password) == false) {
                Toast.makeText(this, "This profile does not exist, please click Create New Profile to create a new profile", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun createNewProfile(view: View) {
        val intent = Intent(this, CreateNewProfilePage::class.java)
        startActivity(intent)
    }

    fun loginAdmin(view: View) {
        val intent = Intent(this, AdminLoginPage::class.java)
        startActivity(intent)
    }

}