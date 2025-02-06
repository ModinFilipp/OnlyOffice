package com.example.onlyofficetest.core

import com.example.onlyofficetest.data.remote.AuthApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null

    private fun createRetrofitInstance(baseUrl: String): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun updateBaseUrl(newUrl: String) {
        GlobalConfig.baseUrl = newUrl
        retrofit = createRetrofitInstance(newUrl)
    }

    private fun getRetrofitInstance(): Retrofit {
        val currentUrl = GlobalConfig.baseUrl
        if (retrofit == null || retrofit?.baseUrl().toString() != currentUrl) {
            retrofit = createRetrofitInstance(currentUrl)
        }
        return retrofit!!
    }

    val api: AuthApiService by lazy {
        getRetrofitInstance().create(AuthApiService::class.java)
    }
}