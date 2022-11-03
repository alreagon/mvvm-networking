package com.example.mvvm_networking.local

import com.example.mvvm_networking.model.UserResponse

class LocalDaoHelper(private val userDao: UserDao) {

    fun getAllUserDaoHelper() = userDao.getAllUsers()
    suspend fun deleteAndInsertData(users: List<UserResponse>) =
        userDao.deleteAndInsertDataUser(users)
}