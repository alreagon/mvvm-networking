package com.example.mvvm_networking.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm_networking.model.UserResponse

@Dao
interface UserDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: UserResponse?)
//
//    @Query("SELECT * FROM user")
//    suspend fun getUser() : List<UserResponse>


    @Query("SELECT * FROM user")
    fun getAllProducts(): List<UserResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: List<UserResponse>)

    @Query("DELETE FROM user")
    suspend fun deleteAllProduct()

    @Transaction
    suspend fun deleteAndInsertData(products: List<UserResponse>) {
        deleteAllProduct()
        insertProduct(products)
    }
}