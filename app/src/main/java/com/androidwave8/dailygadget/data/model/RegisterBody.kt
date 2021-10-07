package com.androidwave8.dailygadget.data.model

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
