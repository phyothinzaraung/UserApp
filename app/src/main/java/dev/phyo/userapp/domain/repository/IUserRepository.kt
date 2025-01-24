package dev.phyo.userapp.domain.repository

import dev.phyo.userapp.data.model.User
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUsers(): Flow<DataResult<List<User>>>
}