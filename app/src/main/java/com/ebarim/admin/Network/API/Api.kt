package com.ebarim.admin.Network.API

import com.ebarim.admin.Network.Data.LoginData
import com.ebarim.admin.Network.Data.ROLE.RoleDataClass
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {
    @FormUrlEncoded
    @POST("login")
    suspend fun adminLogin(
        @Field("id") email: String,
        @Field("password") password: String,
    ): Response<LoginData>

    @GET("me")
    suspend fun role(
        @Header("Authorization") BearerToken: String
    ): Response<RoleDataClass>

    object ApiAdapter {
        val apiClient: ApiClient = Retrofit.Builder()
            .baseUrl("https://293a-2409-4065-d1e-65b6-fbf9-c50f-5dc2-60b6.ngrok.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiClient::class.java)
    }
}