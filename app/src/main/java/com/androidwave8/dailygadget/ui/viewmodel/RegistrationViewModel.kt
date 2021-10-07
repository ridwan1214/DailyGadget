package com.androidwave8.dailygadget.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidwave8.dailygadget.data.model.RegisterBody
import com.androidwave8.dailygadget.data.model.RegisterResponse
import com.androidwave8.dailygadget.data.remote.BinarService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RegistrationViewModel(
    private val service: BinarService,
) : ViewModel() {

    val resultRegister = MutableLiveData<RegisterResponse>()
    val resultError = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()

    fun register(body: RegisterBody) {
        loading.value = true
        service
            .getBinarRegister(body)
            .enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        resultRegister.value = data
                    } else {
                        resultError.value = Throwable(response.errorBody()?.string())
                    }
                    loading.value = false
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    resultError.value = t
                    loading.value = false
                }

            })
    }

    class RegisterFactory(
        private val service: BinarService,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RegistrationViewModel(service) as T
        }
    }

}


