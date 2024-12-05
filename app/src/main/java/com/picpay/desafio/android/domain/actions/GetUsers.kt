package com.picpay.desafio.android.domain.actions

import com.picpay.desafio.android.data.repository.UsersRepository
import com.picpay.desafio.android.domain.model.User
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository: UsersRepository,
){
    suspend operator fun invoke() : Result<List<User>> {
        return try {
            val users = repository.getUsers()
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}