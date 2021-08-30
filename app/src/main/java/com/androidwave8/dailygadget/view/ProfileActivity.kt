package com.androidwave8.dailygadget.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidwave8.dailygadget.databinding.ActivityProfileBinding
import com.androidwave8.dailygadget.presenter.ProfilePresenter
import com.androidwave8.dailygadget.utils.getTextToString


class ProfileActivity : AppCompatActivity(),ProfileActivityView {
    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = ProfilePresenter(this)

        val username = binding.tvUsernameData.text.toString()
        val password = binding.tvPasswordData.text.toString()
        val email = binding.tvEmailData.text.toString()
        TODO("bug on address data")
        //val address = binding.tvAddressData

        presenter.getUser(email, password)


    }

    override fun getProfileSuccess() {
        Intent(this, DummyActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }

    override fun getProfileFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}