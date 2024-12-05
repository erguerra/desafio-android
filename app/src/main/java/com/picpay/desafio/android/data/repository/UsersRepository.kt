package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.mappers.UserMapper
import com.picpay.desafio.android.data.network.UsersRemoteDataSource
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource,
    private val userMapper: UserMapper,
) {
    suspend fun getUsers(): List<User> = remoteDataSource.getUsers().map {
            userMapper.toDomain(it)
        }
}