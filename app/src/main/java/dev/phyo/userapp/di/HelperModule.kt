package dev.phyo.userapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.data.remote.UserHelperImpl
import dev.phyo.userapp.data.remote.UserService

@Module
@InstallIn(ViewModelComponent::class)
object HelperModule {

    @Provides
    fun providesUserHelper(userService: UserService): UserHelper = UserHelperImpl(userService)
}