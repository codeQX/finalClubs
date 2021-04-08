package com.lepu.android.common.http.api

import androidx.paging.DataSource
import com.lepu.android.common.data.model.UserGithub
import com.lepu.lib.core.net.SSLSocketClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiDemo {

    @GET("users")
//    fun getUserList():DataSource.Factory<Int, UserGithub>
    fun getUserList():Call<List<UserGithub>>

    companion object{
        private const val BASE_URL = "https://api.github.com/"
        private const val TIME_OUT: Long = 30

        fun create():ApiDemo{
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(
                    SSLSocketClient.getSSLSocketFactory(),
                    SSLSocketClient.getTrustManager()
                )
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiDemo::class.java)
        }
    }
}