package dev.phyo.userapp.data.repository

import dev.phyo.userapp.data.local.UserDao
import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.domain.mapper.toDomainModel
import dev.phyo.userapp.domain.mapper.toEntityModel
import dev.phyo.userapp.domain.repository.IUserRepository
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userHelper: UserHelper,
    private val userDao: UserDao
) : IUserRepository {

    override suspend fun getUsers(): Flow<DataResult<List<User>>> {
        return flow<DataResult<List<User>>> {
            emit(DataResult.Loading())

            userDao.getUsers()
                .takeIf { it.isNotEmpty() }
                ?.let { localData ->
                    emit(DataResult.Success(localData.toDomainModel()))
                    return@flow
                }

            runCatching {
                userHelper.getUsers()
            }.onSuccess { response ->
                if (response.isSuccessful){
                    val users = response.body()?.data.orEmpty()
                    if (users.isNotEmpty()){
                        userDao.insertUser(users.toEntityModel())
                        emit(DataResult.Success(users))
                    }else{
                        emit(DataResult.Error("API returned empty Data"))
                    }
                }else{
                    emit(DataResult.Error("API error: ${response.message()}"))
                }
            }.onFailure {
                emit(DataResult.Error("Error fetching data: ${it.message}"))
            }
        }.catch {
            emit(DataResult.Error("Unexpected Error: ${it.message}"))
        }
    }
}