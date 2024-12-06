package com.picpay.desafio.android.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val img: String,
    val name: String,
    val username: String,
    val timestamp: Long = System.currentTimeMillis(),
)