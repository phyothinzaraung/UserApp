package dev.phyo.userapp.data.remote

import dev.phyo.userapp.data.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<UserResponse>
}