package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.models.UserResponse
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}