package com.picpay.desafio.android.fixtures

import com.picpay.desafio.android.data.models.UserEntity
import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.domain.model.User

interface UserFixture {

    fun timeStamp() = 1733444404088L

    fun domainUser() = User(
        id = 1,
        name = "Sandrine Spinka",
        img = "https://randomuser.me/api/portraits/men/1.jpg",
        username = "Tod86",
    )

    fun userEntity(timestamp: Long = timeStamp()) = UserEntity(
        id = 1,
        name = "Sandrine Spinka",
        img = "https://randomuser.me/api/portraits/men/1.jpg",
        username = "Tod86",
        timestamp = timestamp,
    )

    fun userResponse() = UserResponse(
        id = 1,
        name = "Sandrine Spinka",
        img = "https://randomuser.me/api/portraits/men/1.jpg",
        username = "Tod86",
    )

    fun domainUserList(): List<User> = listOf(
        User(
            id = 1,
            name = "Sandrine Spinka",
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            username = "Tod86",
        ),
        User(
            id = 2,
            name = "Carli Carroll",
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            username = "Constantin_Sawayn",
        ),
        User(
            id = 3,
            name = "Annabelle Reilly",
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            username = "Lawrence_Nader62",
        ),
        User(
            id = 4,
            name = "Mrs. Hilton Welch",
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            username = "Tatyana_Ullrich",
        ),
        User(
            id = 5,
            name = "Ms. Simeon Yost",
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            username = "Yasmine_Von5",
        ),
    )

    fun locallyPersistedUsers(timestamp: Long = timeStamp()): List<UserEntity> = listOf(
        UserEntity(
            id = 1,
            name = "Sandrine Spinka",
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            username = "Tod86",
            timestamp = timestamp,
        ),
        UserEntity(
            id = 2,
            name = "Carli Carroll",
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            username = "Constantin_Sawayn",
            timestamp = timestamp,
        ),
        UserEntity(
            id = 3,
            name = "Annabelle Reilly",
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            username = "Lawrence_Nader62",
            timestamp = timestamp,
        ),
        UserEntity(
            id = 4,
            name = "Mrs. Hilton Welch",
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            username = "Tatyana_Ullrich",
            timestamp = timestamp,
        ),
        UserEntity(
            id = 5,
            name = "Ms. Simeon Yost",
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            username = "Yasmine_Von5",
            timestamp = timestamp,
        ),
    )

    fun remoteUsers(): List<UserResponse> = listOf(
        UserResponse(
            id = 1,
            name = "Sandrine Spinka",
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            username = "Tod86",
        ),
        UserResponse(
            id = 2,
            name = "Carli Carroll",
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            username = "Constantin_Sawayn",
        ),
        UserResponse(
            id = 3,
            name = "Annabelle Reilly",
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            username = "Lawrence_Nader62",
        ),
        UserResponse(
            id = 4,
            name = "Mrs. Hilton Welch",
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            username = "Tatyana_Ullrich",
        ),
        UserResponse(
            id = 5,
            name = "Ms. Simeon Yost",
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            username = "Yasmine_Von5",
        ),
        UserResponse(
            id = 6,
            name = "Mr. Ewell Reynolds",
            img = "https://randomuser.me/api/portraits/men/6.jpg",
            username = "Alysson50",
        ),
    )
}