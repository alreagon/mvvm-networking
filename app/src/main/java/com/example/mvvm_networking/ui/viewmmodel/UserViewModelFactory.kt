package com.example.mvvm_networking.ui.viewmmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_networking.local.UserRepository
import com.example.mvvm_networking.utils.NetworkHelper

//@Suppress("UNCHECKED_CAST")
//class UserViewModelFactory(
//    private val userRepository: UserRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            return UserViewModel(userRepository) as T
//        }
//        throw IllegalArgumentException("viewModel class not found.")
//    }
//}