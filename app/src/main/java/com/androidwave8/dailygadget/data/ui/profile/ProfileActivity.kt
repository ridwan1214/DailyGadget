package com.androidwave8.dailygadget.data.ui.profile
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidwave8.dailygadget.databinding.ActivityProfileBinding
import com.androidwave8.dailygadget.data.ui.home.HomeActivity
import com.androidwave8.dailygadget.utils.SharePref



class ProfileActivity : AppCompatActivity(), ProfileActivityView {
    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUsernameData.text = SharePref.username
        binding.tvEmailData.text = SharePref.email
        binding.tvPasswordData.text = SharePref.password
        binding.tvAddressData.text = SharePref.address

        binding.ivBackArrow.setOnClickListener {
            Intent(this, HomeActivity::class.java).apply{
                startActivity(this)
            }
        }

    }

    override fun getProfileSuccess() {
        /*Intent(this, DummyActivity::class.java).apply {
            startActivity(this)
        }*/
        finish()
    }

    override fun getProfileFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}