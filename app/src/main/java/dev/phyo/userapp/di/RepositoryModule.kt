package dev.phyo.userapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.phyo.userapp.data.local.UserDao
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.data.repository.UserRepositoryImpl
import dev.phyo.userapp.domain.repository.IUserRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesUserRepository(userHelper: UserHelper, userDao: UserDao): IUserRepository = UserRepositoryImpl(userHelper, userDao)
}