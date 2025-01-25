package dev.phyo.userapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.phyo.userapp.data.local.UserDao
import dev.phyo.userapp.data.local.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesUserDao(db: UserDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context): UserDatabase{
        return Room.databaseBuilder(
            context = context,
            UserDatabase::class.java,
            "user.db"
        ).build()
    }
}