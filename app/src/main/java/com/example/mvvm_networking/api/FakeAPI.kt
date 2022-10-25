package com.example.mvvm_networking.api

import com.example.mvvm_networking.model.FakeResponse
import com.example.mvvm_networking.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FakeAPI {
    @GET("posts")
    fun getFakeApi(): Call<List<FakeResponse>>

    @GET("users")
    fun getUsersFake(): List<UserResponse>
}