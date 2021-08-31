package com.androidwave8.dailygadget.data.presenter

import android.text.TextUtils
import android.widget.EditText
import com.androidwave8.dailygadget.App
import com.androidwave8.dailygadget.ui.login.LoginActivity
import com.androidwave8.dailygadget.utils.SharePref
import com.androidwave8.dailygadget.utils.ambilText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(private val v: LoginActivity){

    fun validasiDataIsEmpty(
        username: EditText,
        password: EditText
    ): Boolean = when {
        TextUtils.isEmpty(username.ambilText()) -> {
            username.error = "username Tidak boleh kosong"
            true
        }
        TextUtils.isEmpty(password.ambilText()) -> {
            username.error = "password Tidak boleh kosong"
            true
        }
        else -> false
    }
    fun login(username: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val user = App.dbUser
                    .usersDao()
                    .getUser(username, password)

                launch(Dispatchers.Main) {
                    if (user != null) {
                        SharePref.isLogin = true
                        v.loginSucces()
                    } else {
                        v.loginFailed("User tidak terdaftar")
                    }
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    v.loginFailed(e.message.toString())
                }
            }
        }
    }

}