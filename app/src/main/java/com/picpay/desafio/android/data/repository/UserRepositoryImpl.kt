package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UsersLocalDataSource
import com.picpay.desafio.android.data.mappers.UserMapper
import com.picpay.desafio.android.data.network.UsersRemoteDataSource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource,
    private val localDataSource: UsersLocalDataSource,
    private val userMapper: UserMapper,
) : UserRepository {

    private val cacheLifeTime by lazy {
        TimeUnit.MINUTES.toMillis(5L)
    }

    override fun getUsers(): Flow<List<User>> = flow {
        localDataSource.getAllUsers().catch {
            emit(emptyList())
        }.collect { allUsers ->
            if(allUsers.isEmpty() || allUsers.any { needsUpdate(it.timestamp)}) {
                fetchAndCacheUsers()
            } else {
                emit(allUsers.map(userMapper::toDomain))
            }
        }
    }

    override suspend fun fetchAndCacheUsers() {
        remoteDataSource.getUsers().collect { remoteUsers ->
            localDataSource.saveUsers(remoteUsers.map(userMapper::toEntity))
        }
    }

    private fun needsUpdate(lastUpdated: Long) : Boolean {
        return System.currentTimeMillis() - lastUpdated > cacheLifeTime
    }
}