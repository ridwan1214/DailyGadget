package com.androidwave8.dailygadget.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidwave8.dailygadget.data.remote.BinarService
import com.androidwave8.dailygadget.data.model.LoginBody
import com.androidwave8.dailygadget.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val service: BinarService,
) : ViewModel() {

    val resultLogin = MutableLiveData<LoginResponse>()
    val resultError = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()

    fun login(body: LoginBody) {
        loading.value = true
        service
            .getBinarLogin(body)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    if (response.isSuccessful) {
                        val data = response.body() // = MoviesRespose
                        resultLogin.value = data
                    } else {
                        resultError.value = Throwable(response.errorBody()?.string())
                    }
                    loading.value = false
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    resultError.value = t
                    loading.value = false
                }
            })
    }

    class LoginFactory(
        private val service: BinarService,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(service) as T
        }
    }
}