//package com.example.mvvm_networking.ui.viewmmodel
//
//import android.app.Application
//import com.example.mvvm_networking.api.ApiConfig2
//import com.example.mvvm_networking.api.FakeAPI
//import com.example.mvvm_networking.local.UserDatabase
//import com.example.mvvm_networking.local.UserRepository
//
//class MyApplication : Application() {
//
//    lateinit var userRepository: UserRepository
//
//    override fun onCreate() {
//        super.onCreate()
//
//        val apiInterface = ApiConfig2.getInstance().create(FakeAPI::class.java)
//
//        val database = UserDatabase.getDatabase(applicationContext)
//
//        userRepository = UserRepository(apiInterface, database)
//    }
//}