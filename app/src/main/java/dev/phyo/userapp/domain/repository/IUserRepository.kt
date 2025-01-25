package dev.phyo.userapp.domain.repository

import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getAndSaveUser()
    suspend fun getSavedUsers(): Flow<DataResult<List<User>>>
}