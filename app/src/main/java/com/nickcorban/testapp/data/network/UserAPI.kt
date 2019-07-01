package com.nickcorban.testapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nickcorban.testapp.data.database.entry.Entry
import com.nickcorban.testapp.data.network.interceptors.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserAPI{

    @GET("/users")
    fun getContacts() : Deferred<List<Entry>>


    companion object {
        operator fun invoke(connInterceptor: ConnectivityInterceptor) : UserAPI {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI::class.java)
        }
    }

}