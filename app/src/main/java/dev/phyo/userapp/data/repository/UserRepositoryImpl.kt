package dev.phyo.userapp.data.repository

import android.provider.ContactsContract.Data
import dev.phyo.userapp.data.local.UserDao
import dev.phyo.userapp.data.local.UserEntity
import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.domain.repository.IUserRepository
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userHelper: UserHelper,
    private val userDao: UserDao
): IUserRepository {

    override suspend fun getAndSaveUser() {
        try {
            userHelper.getUsers().let { response ->
                if (response.isSuccessful){
                    response.body()?.data?.let { users ->
                        val userEntities = users.map {
                            UserEntity(
                                it.id,
                                it.email,
                                it.first_name,
                                it.last_name,
                                it.avatar
                            )
                        }
                        userDao.insertUser(userEntities)
                    }
                }
            }
        }catch (e: Exception){
            throw Exception("Error fetching or saving data: ${e.message}")
        }

    }

    override suspend fun getSavedUsers(): Flow<DataResult<List<User>>> {
        return flow {
            emit(DataResult.Loading())
            with(userDao.getUsers()){
                emit(DataResult.Success(this.map { User(
                    it.id,
                    it.email,
                    it.first_name,
                    it.last_name,
                    it.avatar
                ) }))
            }
        }.catch {
            emit(DataResult.Error(it.message))
        }
    }
}