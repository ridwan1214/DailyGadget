package com.androidwave8.dailygadget.data.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidwave8.dailygadget.databinding.ActivityRegistrationBinding
import com.androidwave8.dailygadget.data.db.User
import com.androidwave8.dailygadget.data.ui.home.HomeActivity
import com.androidwave8.dailygadget.utils.getTextToString


class RegistrationActivity : AppCompatActivity(), RegistrationActivityView {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = RegistrationPresenter(this)

        binding.btnSubmit.setOnClickListener {
            val username = binding.etUsername
            val password = binding.etPass
            val email = binding.etEmail
            val address = binding.etAddress

            if (presenter.fieldIsEmpty(username, password, email, address)) {
                Toast.makeText(this, "Data must be filled", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(
                    username.getTextToString(),
                    password.getTextToString(),
                    email.getTextToString(),
                    address.getTextToString()
                )
                presenter.register(user)

            }
        }
    }


    override fun regSuccess() {
        Toast.makeText(this, "Successfully created a new user", Toast.LENGTH_SHORT).show()
        Intent(this, HomeActivity::class.java).apply{
            startActivity(this)
        }
        finish()
    }

    override fun regFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}