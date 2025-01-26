package dev.phyo.userapp.domain.repository

import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUsers(): Flow<DataResult<List<User>>>
    suspend fun getUserById(userId: Int): Flow<DataResult<User>>
}