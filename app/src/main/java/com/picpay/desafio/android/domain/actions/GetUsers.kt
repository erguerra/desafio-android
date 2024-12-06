package com.picpay.desafio.android.domain.actions

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<List<User>> = repository.getUsers()
}