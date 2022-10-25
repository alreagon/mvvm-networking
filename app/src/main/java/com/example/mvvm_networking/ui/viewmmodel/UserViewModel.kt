package com.example.mvvm_networking.ui.viewmmodel

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

class UserViewModel(
private val userRepository: UserRepository,
private val networkHelper: NetworkHelper

) : ViewModel(){
    private val _getProductOffline = MutableLiveData<Resource<List<UserResponse>>>()
    val gettProductOffline: LiveData<Resource<List<UserResponse>>>
        get() = _getProductOffline


    fun getProductOfflineAll() {
        viewModelScope.launch {
            _getProductOffline.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = userRepository.getProductBoundResource()
                    _getProductOffline.postValue(Resource.success(response))
                } catch (e: Exception) {
                    _getProductOffline.postValue(Resource.error("failed to get data from server",null))
                }
            } else {
                val response = userRepository.getProductOffline()
                _getProductOffline.postValue(Resource.success(response))
            }
        }
    }
}

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