package com.androidwave8.dailygadget.data.remote


import com.androidwave8.dailygadget.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {
    private var retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_BINAR)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var binarService = retrofit.create(BinarService::class.java)
    private val logging: HttpLoggingInterceptor
        get() {
            return HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
}