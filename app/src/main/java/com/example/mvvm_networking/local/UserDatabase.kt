package com.example.mvvm_networking.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_networking.model.UserResponse

@Database(entities = [UserResponse::class], version = 1)
//@TypeConverters(CategoryTypeConverter::class,NotificationTypeConverter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}

//@Database(entities = [UserResponse::class], version = 1)
//abstract class UserDatabase : RoomDatabase() {
//
//    abstract fun getUserDao() : UserDao
//
//    companion object{
//
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase {
//            Log.e("UserDatabase", "UserDatabase")
//
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        UserDatabase::class.java,
//                        "userDB"
//                    ).build()
//                }
//            return INSTANCE!! //ini
//        }
//    }
//}




//@Database(entities = [UserResponse::class], version = 1, exportSchema = false)
//abstract class UserDatabase : RoomDatabase(){
//
//    abstract fun userDao(): UserDao
//}
//
//private lateinit var INSTANCE : UserDatabase
//
//fun getDatabase(context: Context): UserDatabase {
//
//    synchronized(UserDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                UserDatabase::class.java,
//                "database"
//            ).build()
//        }
//    }
//
//    return INSTANCE
//}