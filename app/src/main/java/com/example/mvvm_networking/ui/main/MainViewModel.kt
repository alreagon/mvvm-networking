package com.example.mvvm_networking.ui.main

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_networking.api.ApiConfig
import com.example.mvvm_networking.model.FakeResponse
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    val fakeResponse = MutableLiveData<List<FakeResponse>>()

    init {
        fetchData()
    }

    private fun fetchData() {
        val client = ApiConfig.getApiService().getFakeApi()
        client.enqueue(object : retrofit2.Callback<List<FakeResponse>> {
            override fun onResponse(
                call: Call<List<FakeResponse>>,
                response: Response<List<FakeResponse>>
            ) {
                if (response.isSuccessful) {

                    Log.d("ViewModel", "ViewmModelSuccesfull")
//                    val postss: FakeResponse? = response.body()
                    fakeResponse.value = response.body()

                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FakeResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }


}