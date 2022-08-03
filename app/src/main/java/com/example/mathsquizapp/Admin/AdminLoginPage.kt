package com.example.mathsquizapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mathsquizapp.Model.QuizDatabase
import com.example.mathsquizapp.R

class AdminLoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login_page)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }

    fun adminLogin(view: View) {
        val db: QuizDatabase = QuizDatabase(this)
        val username = findViewById<EditText>(R.id.editText_UsernameAdmin).text.toString()
        val password = findViewById<EditText>(R.id.editText_PasswordAdmin).text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
        } else {
            if (db.getAdmin(username, password) == true) {
        Toast.makeText(this, "Welcome back $username!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AdminAddQuestionsPage::class.java).apply {
            putExtra("Username", username)
        }
        startActivity(intent)
            } else if(db.getAdmin(username, password) == false) {
                Toast.makeText(this, "This Admin profile does not exist, please click Create Admin Profile to create a new Admin profile", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun createNewProfileAdmin(view: View) {
        val db: QuizDatabase = QuizDatabase(this)
        val username = findViewById<EditText>(R.id.editText_UsernameAdmin).text.toString()
        val password = findViewById<EditText>(R.id.editText_PasswordAdmin).text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (username.length < 6) {
            Toast.makeText(this, "Username must be more than 6 characters!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
        } else if (password.length < 8 ||
            !(password.any { it.isUpperCase() }) ||
            !(password.any { it.isUpperCase() }) ||
            !(password.any { it.isDigit() }) ){
            Toast.makeText(this, "Password must consist be at least 8 characters," +
                    "contain uppercase letter(s), lowercase letter(s), and digit(s).", Toast.LENGTH_SHORT).show()
        } else {
            if (db.checkAdminExists(username) == false && db.getAdmin(username, password) == false) {
                db.addAdmin(username, password)
                Toast.makeText(this, "Successfully created your new Admin profile $username!. Please login above to access your profile.", Toast.LENGTH_LONG).show()
            } else if (db.checkAdminExists(username) == true || db.getAdmin(username, password) == true) {
                Toast.makeText(this, "Account already exists! Please click Return To Login to login to this Admin profile", Toast.LENGTH_SHORT).show()
            }
        }
    }

}