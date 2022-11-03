package com.example.mvvm_networking.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm_networking.model.UserResponse

@Dao
interface UserDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(userResponse: UserResponse)
//
//    @Delete
//    suspend fun deleteUser(userResponse: UserResponse)
//
//    @Query("SELECT * FROM user")
//    fun getUser() : LiveData<List<UserResponse>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: UserResponse?)
//
//    @Query("SELECT * FROM user")
//    suspend fun getUser() : List<UserResponse>


    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(products: List<UserResponse>)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

    @Transaction
    suspend fun deleteAndInsertDataUser(products: List<UserResponse>) {
        deleteAllUsers()
        insertUser(products)
    }
}