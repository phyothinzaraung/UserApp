package dev.phyo.userapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)