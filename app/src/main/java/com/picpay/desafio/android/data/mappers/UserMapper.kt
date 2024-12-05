package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.models.UserResponse
import javax.inject.Inject

class UserMapper @Inject constructor(){

    fun toDomain(userResponse: UserResponse) : User = with(userResponse) {
        User(
            id = id,
            name = name,
            img = img,
            username = username,
        )
    }
}