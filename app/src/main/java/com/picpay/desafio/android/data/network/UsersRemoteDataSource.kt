package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.models.UserResponse
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val picPayService: PicPayService
) {

    suspend fun getUsers(): List<UserResponse> = picPayService.getUsers()
}