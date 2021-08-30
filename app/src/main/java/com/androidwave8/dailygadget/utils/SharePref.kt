package com.androidwave8.dailygadget.utils

import android.content.Context
import com.androidwave8.dailygadget.App

object SharePref {
    private val pref = App.context.getSharedPreferences("mypref", Context.MODE_PRIVATE)

    var isLogin: Boolean
        set(value) {
            pref.edit()
                .putBoolean("ISLOGIN", value)
                .apply()
        }
        get() = pref
            .getBoolean("ISLOGIN", false)

    fun removeLogin() {
        pref.edit().remove("ISLOGIN").apply()
    }
}