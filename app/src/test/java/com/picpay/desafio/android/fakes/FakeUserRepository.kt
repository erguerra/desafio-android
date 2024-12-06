package com.picpay.desafio.android.fakes

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.fixtures.UserFixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserRepository : UserRepository, UserFixture {

    override fun getUsers(): Flow<List<User>> = flow {
        emit(domainUserList())
    }

    override suspend fun fetchAndCacheUsers() {
        TODO("Not yet implemented")
    }

}