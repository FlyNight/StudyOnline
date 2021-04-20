package com.example.studyonline.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.studyonline.MainActivity
import com.example.studyonline.R

val TYPE:Array<String> = arrayOf("REGISTER", "RESET")
class RegisterOrResetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_or_reset)
        when(intent.getStringExtra("type")) {
            TYPE[0] -> initRegister()
            TYPE[1] -> initReset()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRegister() {
        val mainTitle: TextView = findViewById(R.id.main_title)
        val mainButton: Button = findViewById(R.id.main_button)
        val leftButton: Button = findViewById(R.id.left_button)
        val rightButton: Button = findViewById(R.id.right_button)
        mainTitle.text = TYPE[0]
        mainButton.text = "Register"
        leftButton.text = "BACK TO LOGIN"
        rightButton.text = "FORGET PASSWORD?"
        mainButton.isEnabled = true
        mainButton.setOnClickListener {
            Toast.makeText(applicationContext, "Register successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        leftButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        rightButton.setOnClickListener {
            startActivity(Intent(this, RegisterOrResetActivity::class.java).putExtra("type","RESET"))
        }
    }
    @SuppressLint("SetTextI18n")
    private fun initReset() {
        val mainTitle: TextView = findViewById(R.id.main_title)
        val mainButton: Button = findViewById(R.id.main_button)
        val leftButton: Button = findViewById(R.id.left_button)
        val rightButton: Button = findViewById(R.id.right_button)
        mainTitle.text = TYPE[1]
        mainButton.text = "Reset"
        leftButton.text = "BACK TO LOGIN"
        rightButton.text = "REGISTER"
        mainButton.isEnabled = true
        mainButton.setOnClickListener {
            Toast.makeText(applicationContext, "Reset successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        leftButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        rightButton.setOnClickListener {
            startActivity(Intent(this, RegisterOrResetActivity::class.java).putExtra("type","REGISTER"))
        }
    }

    fun backToLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun forgetPassword(view: View) {
        startActivity(Intent(this, RegisterOrResetActivity::class.java).putExtra("type","RESET"))
    }
}