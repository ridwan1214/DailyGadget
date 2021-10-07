package com.androidwave8.dailygadget.data.remote

import com.androidwave8.dailygadget.BuildConfig
import com.androidwave8.dailygadget.data.model.LoginBody
import com.androidwave8.dailygadget.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BinarService {
    // karna multi baseurl jadi base url ditaro disini bukan di api module
    @POST("${BuildConfig.BASE_URL_BINAR}api/v1/auth/login")
    fun getBinarLogin(@Body loginBody: LoginBody): Call<LoginResponse>

    /*@PUT("${BuildConfig.BASE_URL_BINAR}api/v1/users")
    fun putProfile(
        @Header("Authorization") token: String,
        @Body body: RequestBody
    ): Call<ProfileResponse>*/
}