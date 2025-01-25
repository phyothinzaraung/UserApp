package dev.phyo.userapp.data.remote

import dev.phyo.userapp.data.remote.model.UserResponse
import retrofit2.Response

class UserHelperImpl(private val userService: UserService): UserHelper {
    override suspend fun getUsers(): Response<UserResponse> {
        return userService.getUsers()
    }
}