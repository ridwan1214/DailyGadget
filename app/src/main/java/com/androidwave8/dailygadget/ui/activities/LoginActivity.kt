package com.androidwave8.dailygadget.ui.activities



import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.androidwave8.dailygadget.R
import com.androidwave8.dailygadget.data.model.User
import com.androidwave8.dailygadget.data.firestore.FirestoreClass
import com.androidwave8.dailygadget.data.remote.ApiModule
import com.androidwave8.dailygadget.data.model.LoginBody
import com.androidwave8.dailygadget.databinding.ActivityLoginBinding
import com.androidwave8.dailygadget.ui.viewmodel.LoginViewModel
import com.androidwave8.dailygadget.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var factory: LoginViewModel.LoginFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


        factory = LoginViewModel.LoginFactory(ApiModule.binarService)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]


        viewModel.resultLogin.observe(this) {
            Toast.makeText(this, "${it.data}", Toast.LENGTH_SHORT).show()
        }

        viewModel.resultError.observe(this) {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.tv_forgot_password -> {
                    Intent(this, ForgotPasswordActivity::class.java).apply {
                        startActivity(this)
                    }
                }

                R.id.btn_login -> {
                    val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
                    val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

                    val body = LoginBody(email, password)
                    viewModel.login(body)
                    loginRegisteredUser()

                }

                R.id.tv_register -> {
                    Intent(this, RegistrationActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }

    private fun loginRegisteredUser() {

        if (validateLoginDetails()) {

            showProgresDialog(resources.getString(R.string.please_wait))

            // dapat text dari editext
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

            // login pakai firebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        FirestoreClass().getUserDetails(this@LoginActivity)

                    } else {
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                true
            }
        }
    }

    fun userLoggedInSuccess(user: User) {

        hideProgressDialog()

        if (user.profileCompleted == 0) {
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        } else {
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }
        finish()
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}