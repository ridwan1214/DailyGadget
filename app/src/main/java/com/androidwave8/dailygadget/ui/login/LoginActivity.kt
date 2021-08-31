package com.androidwave8.dailygadget.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidwave8.dailygadget.data.presenter.LoginPresenter
import com.androidwave8.dailygadget.data.ui.home.HomeActivity
import com.androidwave8.dailygadget.data.ui.register.RegistrationActivity
import com.androidwave8.dailygadget.databinding.ActivityLoginBinding
import com.androidwave8.dailygadget.utils.SharePref
import com.androidwave8.dailygadget.utils.ambilText

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = LoginPresenter(this)

        if (SharePref.isLogin) {
            Intent(this, HomeActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername
            val password = binding.etPassword

            if (presenter.validasiDataIsEmpty(username, password)) {
                Toast.makeText(this, "Silahkan isi data dengan benar", Toast.LENGTH_SHORT).show()
            } else {
                presenter.login(username.ambilText(), password.ambilText())
            }
        }

        binding.tvRegister.setOnClickListener {
            Intent(this, RegistrationActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun loginSucces() {
        Intent(this, HomeActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }

    override fun loginFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}