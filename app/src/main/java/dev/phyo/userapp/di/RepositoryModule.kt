package dev.phyo.userapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.data.remote.repository.UserRepositoryImpl
import dev.phyo.userapp.domain.repository.IUserRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesUserRepository(userHelper: UserHelper): IUserRepository = UserRepositoryImpl(userHelper)
}