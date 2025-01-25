package dev.phyo.userapp.data.remote

import dev.phyo.userapp.data.remote.model.UserResponse
import retrofit2.Response

interface UserHelper {
    suspend fun getUsers(): Response<UserResponse>
}