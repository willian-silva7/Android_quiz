package com.example.android_quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    // Timer of Splash Screen
    private val splashTimeOut: Int = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                // After the time ends, start the Main Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, splashTimeOut.toLong()
        )
    }
}