package com.example.mvvm_networking.ui.viewmmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_networking.local.UserRepository
import com.example.mvvm_networking.model.UserResponse
import com.example.mvvm_networking.utils.NetworkHelper
import com.example.mvvm_networking.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log


class UserViewModel(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper

) : ViewModel() {
    private val _getProductOffline = MutableLiveData<Resource<List<UserResponse>>>()
    val gettProductOffline: LiveData<Resource<List<UserResponse>>>
        get() = _getProductOffline


    fun getUserOfflineAll() {
        viewModelScope.launch {
            _getProductOffline.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = userRepository.getUserRemote()
                    _getProductOffline.postValue(Resource.success(response))
                } catch (e: Exception) {
                    _getProductOffline.postValue(
                        Resource.error(
                            "failed to get data from server",
                            null
                        )
                    )
                }
            } else {
                try {
                    val response = userRepository.getProductOffline()
                    _getProductOffline.postValue(Resource.success(response))
                }catch (e:Exception){
                    _getProductOffline.postValue(Resource.error("Fail", null))
                }

            }
        }
    }
}


//class UserViewModel(
//    private val userRepository: UserRepository
//) : ViewModel() {
//
//    fun getUser(): LiveData<List<UserResponse>> {
//        return userRepository.getUser()
//    }
//
//    fun insertUser(userResponse: UserResponse) {
//        Log.e("UserViewModel", "UserViewModel $userResponse")
//        viewModelScope.launch {
//            userRepository.insertUser(userResponse)
//        }
//    }
//}


//class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
//    init {
//
//        viewModelScope.launch(Dispatchers.IO) {
//            userRepository.getUser()
//        }
//    }
//
//    val user : LiveData<UserResponse>
//    get() = userRepository.user
//
//
//}