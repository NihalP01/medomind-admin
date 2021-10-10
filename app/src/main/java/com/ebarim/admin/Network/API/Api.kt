package com.ebarim.admin.Network.API

import com.ebarim.admin.Network.Data.Common
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

    @FormUrlEncoded
    @POST("doctor/register")
    suspend fun register(
        @Header("Authorization") BearerToken: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<Common>

    object ApiAdapter {
        val apiClient: ApiClient = Retrofit.Builder()
            .baseUrl("http://doc-book.techmess.in/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiClient::class.java)
    }
}