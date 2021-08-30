package com.androidwave8.dailygadget.utils

import android.content.Context
import com.androidwave8.dailygadget.App

object SharePref {

    private val pref = App.context.getSharedPreferences("myPref", Context.MODE_PRIVATE)

    var isLogin: Boolean
        set(value) {
            pref.edit()
                .putBoolean("ISLOGIN", value)
                .apply()
        }
        get() = pref
            .getBoolean("ISLOGIN", false)

    var username: String?
        set(value) {
            pref.edit()
                .putString("USERNAME", value)
                .apply()
        }
        get() = pref
            .getString("USERNAME", "")

    var password: String?
        set(value) {
            pref.edit()
                .putString("PASSWORD", value)
                .apply()
        }
        get() = pref
            .getString("PASSWORD", "")

}