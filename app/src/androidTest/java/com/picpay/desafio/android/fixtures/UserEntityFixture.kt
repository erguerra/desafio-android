package com.picpay.desafio.android.fixtures

import com.picpay.desafio.android.data.models.UserEntity

interface UserEntityFixture {

    fun userEntityList() : List<UserEntity> = listOf(
        UserEntity(
            id = 1,
            name = "Sandrine Spinka",
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            username = "Tod86",
        ),
        UserEntity(
            id = 2,
            name = "Carli Carroll",
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            username = "Constantin_Sawayn",
        ),
        UserEntity(
            id = 3,
            name = "Annabelle Reilly",
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            username = "Lawrence_Nader62",
        ),
        UserEntity(
            id = 4,
            name = "Mrs. Hilton Welch",
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            username = "Tatyana_Ullrich",
        ),
        UserEntity(
            id = 5,
            name = "Ms. Simeon Yost",
            img = "https://randomuser.me/api/portraits/men/5.jpg",
            username = "Yasmine_Von5",
        ),
        UserEntity(
            id = 6,
            name = "Mr. Ewell Reynolds",
            img = "https://randomuser.me/api/portraits/men/6.jpg",
            username = "Alysson50",
        ),
        UserEntity(
            id = 7,
            name = "Brad Gibson",
            img = "https://randomuser.me/api/portraits/men/7.jpg",
            username = "Ayden.Gutkowski46",
        ),
        UserEntity(
            id = 8,
            name = "Adolphus Collier",
            img = "https://randomuser.me/api/portraits/men/8.jpg",
            username = "Irwin3",
        ),
        UserEntity(
            id = 9,
            name = "Chaya Auer",
            img = "https://randomuser.me/api/portraits/men/9.jpg",
            username = "Durward2",
        ),
        UserEntity(
            id = 10,
            name = "Juston Nicolas",
            img = "https://randomuser.me/api/portraits/men/10.jpg",
            username = "Eleonore.Schmitt",
        ),
    )
}