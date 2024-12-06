package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.models.UserEntity
import com.picpay.desafio.android.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersLocalDataSource @Inject constructor(
    private val usersDao: UserDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) {

    fun getAllUsers(): Flow<List<UserEntity>> = usersDao.getAllUsers().flowOn(dispatcher)

    suspend fun saveUsers(users: List<UserEntity>) {
        usersDao.insert(users)
    }
}