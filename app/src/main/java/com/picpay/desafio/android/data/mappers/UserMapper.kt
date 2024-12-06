package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.models.UserEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.models.UserResponse
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun toDomain(userEntity: UserEntity): User = with(userEntity) {
        User(
            id = id,
            name = name,
            img = img,
            username = username,
        )
    }

    fun toEntity(userResponse: UserResponse): UserEntity = with(userResponse) {
        UserEntity(
            id = id,
            name = name,
            img = img,
            username = username,
        )
    }
}