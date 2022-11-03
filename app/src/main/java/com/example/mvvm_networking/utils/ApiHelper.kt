package com.example.mvvm_networking.utils

import com.example.mvvm_networking.api.FakeAPI
import com.example.mvvm_networking.model.UserResponse

class ApiHelper(private val fakeAPI: FakeAPI) {

    suspend fun getUserHelper() : List<UserResponse> {
        return fakeAPI.getUsersFake()
    }

}