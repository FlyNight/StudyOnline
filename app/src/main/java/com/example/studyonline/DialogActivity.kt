package com.example.studyonline

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast

class DialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT)
    }

    fun cancelLogin(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun login(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun register(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun forgetPassword(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}