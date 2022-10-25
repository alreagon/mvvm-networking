//package com.example.mvvm_networking.ui.viewmmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.mvvm_networking.local.UserRepository
//
//class UserViewModelFactory(private val userRepository: UserRepository):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return UserViewModel(userRepository) as T
//    }
//}