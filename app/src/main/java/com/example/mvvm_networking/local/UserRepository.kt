package com.example.mvvm_networking.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_networking.api.FakeAPI
import com.example.mvvm_networking.model.FakeResponse
import com.example.mvvm_networking.model.UserResponse
import com.example.mvvm_networking.utils.ApiHelper


class UserRepository(
    private val localDaoHelper: LocalDaoHelper,
    private val apiHelper: ApiHelper
) {

    suspend fun getProductBoundResource(): List<UserResponse> {
        val response = apiHelper.getProducBoundResource()
        localDaoHelper.deleteAndInsertData(response.take(40))
        return response
    }

    fun getProductOffline(): List<UserResponse> {
        return localDaoHelper.getAllProduct()
    }
}

//class UserRepository(
//    private val fakeAPI: FakeAPI,
//    private val userDatabase: UserDatabase
//) {
//    private val userFake = MutableLiveData<UserResponse>()
//    val user : LiveData<UserResponse>
//        get() = userFake
//
//    suspend fun getUser(){
//        val result = fakeAPI.getUsersFake()
//        if (result.body() != null){
//
//            userDatabase.getUserDao().insertUser(result.body())
//
//            userFake.postValue(result.body())
//        }
//
//    }
//
//
//}