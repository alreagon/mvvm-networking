package com.example.mvvm_networking.local

import com.example.mvvm_networking.model.UserResponse

class LocalDaoHelper(private val userDao: UserDao) {

    // product
    fun getAllProduct() = userDao.getAllProducts()
    suspend fun deleteAndInsertData(products: List<UserResponse>) =
        userDao.deleteAndInsertData(products)
}