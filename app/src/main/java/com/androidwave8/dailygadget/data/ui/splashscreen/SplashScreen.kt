package com.androidwave8.dailygadget.data.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.androidwave8.dailygadget.R
import com.androidwave8.dailygadget.data.ui.onboarding.OnBoarding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intentOnBoarding = Intent(this, OnBoarding::class.java)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                this.finish()
                startActivity(intentOnBoarding)
            }, 3000
        )
    }
}