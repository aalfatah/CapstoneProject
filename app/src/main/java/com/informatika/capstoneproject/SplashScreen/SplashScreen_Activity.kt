package com.informatika.capstoneproject.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.informatika.capstoneproject.MainActivity
import com.informatika.capstoneproject.R

class SplashScreen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed( {
            val intent  =   Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}