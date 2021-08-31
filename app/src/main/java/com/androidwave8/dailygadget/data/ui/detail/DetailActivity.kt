package com.androidwave8.dailygadget.data.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.core.view.WindowCompat
import com.androidwave8.dailygadget.R
import com.androidwave8.dailygadget.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide



class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbarXml = binding.toolbar

        setSupportActionBar(toolbarXml)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Set Image
        Glide
            .with(this)
            .load("https://cf.shopee.co.id/file/c05f4cc931d294035eae9e4f4df1ac2c")
            .into(binding.ivDetail)

        binding.text1.text = getString(R.string.lorem_ipsum)
        binding.text2.text = getString(R.string.lorem_ipsum2)

        val messageTitle = binding.tvDetailName.text.toString()

        // Setting on click listener
        binding.btnBeli.setOnClickListener {
            try {
                val message = "Saya \nnama: \nmau order gadget ini bang! \n$messageTitle"
                val contact = "+6289637536869"
                val whatsappUrl = "https://api.whatsapp.com/send?phone= $contact &text=$message"
                intent = Intent(ACTION_VIEW)
                // Setting whatsapp package name
                intent.setPackage("com.whatsapp")
                intent.data = Uri.parse(whatsappUrl)
                // Starting Whatsapp
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Whatsapp not installed!", Toast.LENGTH_LONG).show()
            }
        }

    }

}