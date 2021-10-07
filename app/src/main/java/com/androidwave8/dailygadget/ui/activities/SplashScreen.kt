package com.androidwave8.dailygadget.ui.activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.androidwave8.dailygadget.R
import com.androidwave8.dailygadget.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        requestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)



        val path = "android.resource://" + packageName + "/" + R.raw.dailygadget
        val uri = Uri.parse(path)

        binding.vvIntro!!.setVideoURI(uri)
        binding.vvIntro!!.requestFocus()
        binding.vvIntro!!.start()

        binding.vvIntro!!.setOnCompletionListener {
            if (isFinishing){
                true
            }
            startActivity(Intent(this,OnBoarding::class.java))
            finish()
        }

    }
}