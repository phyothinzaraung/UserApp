package dev.phyo.userapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phyo.userapp.data.remote.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>
}