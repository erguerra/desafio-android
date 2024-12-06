package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val picPayService: PicPayService,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getUsers(): List<UserResponse> = withContext(dispatcher) {
        picPayService.getUsers()
    }
}