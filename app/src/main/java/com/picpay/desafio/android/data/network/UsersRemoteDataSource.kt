package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.di.IODispatcher
import com.picpay.desafio.android.data.models.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UsersRemoteDataSource @Inject constructor(
    private val picPayService: PicPayService,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getUsers(): List<UserResponse> = withContext(dispatcher) {
        picPayService.getUsers()
    }
}