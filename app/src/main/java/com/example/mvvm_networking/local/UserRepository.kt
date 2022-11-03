package com.example.mvvm_networking.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_networking.api.FakeAPI
import com.example.mvvm_networking.model.FakeResponse
import com.example.mvvm_networking.model.UserResponse
import com.example.mvvm_networking.utils.ApiHelper


class UserRepository (
    private val apiHelper: ApiHelper,
    private val localDaoHelper: LocalDaoHelper
) {
    suspend fun getUserRemote(): List<UserResponse> {
        val response = apiHelper.getUserHelper()
        localDaoHelper.deleteAndInsertData(response)
        return response
    }

    fun getProductOffline(): List<UserResponse> {
        return localDaoHelper.getAllUserDaoHelper()
    }

}

//class UserRepository(private val userDao: UserDao) {
//    suspend fun insertUser(userResponse: UserResponse) {
//        userDao.insertUser(userResponse)
//    }
//
//    fun getUser(): LiveData<List<UserResponse>> {
//        Log.e("UserRepository", "UserRepository")
//        return userDao.getUser()
//    }
//}

//class UserRepository(
//    private val localDaoHelper: LocalDaoHelper,
//    private val apiHelper: ApiHelper
//) {
//
//    suspend fun getProductBoundResource(): List<UserResponse> {
//        val response = apiHelper.getProducBoundResource()
//        localDaoHelper.deleteAndInsertData(response.take(40))
//        return response
//    }
//
//    fun getProductOffline(): List<UserResponse> {
//        return localDaoHelper.getAllProduct()
//    }
//}

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