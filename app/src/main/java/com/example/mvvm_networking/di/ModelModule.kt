package com.example.mvvm_networking.di

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import com.example.mvvm_networking.BuildConfig
import com.example.mvvm_networking.api.FakeAPI
import com.example.mvvm_networking.local.*
import com.example.mvvm_networking.ui.viewmmodel.UserViewModel
import com.example.mvvm_networking.utils.ApiHelper
import com.example.mvvm_networking.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val BASE_URL = "https://jsonplaceholder.typicode.com/"

val repoModule = module {
    single { UserRepository(get(),get()) }
}

val UserModelModule = module {
    viewModel { UserViewModel(get(), get()) }
}

val localDbModule = module {
    single { provideDb(androidContext()) }
    single { provideUserDao(get()) }
    single { provideUserDaoImpl(get()) }
}

private fun provideDb(context: Context) : UserDatabase {
    return Room.databaseBuilder(context,UserDatabase::class.java,"my_db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

private fun provideUserDao(userDatabase: UserDatabase): UserDao {
    return userDatabase.userDao()
}

private fun provideUserDaoImpl(userDao: UserDao): LocalDaoHelper {
    return LocalDaoHelper(userDao)
}

val appModule = module {
    single { provideNetworkHelper(androidContext()) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideApiService(get()) }
    single { ApiHelper(get()) }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .build()

private fun provideRetrofit (
    okHttpClient: OkHttpClient,
    BASE_URl: String
) : Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URl)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): FakeAPI = retrofit.create(FakeAPI::class.java)

