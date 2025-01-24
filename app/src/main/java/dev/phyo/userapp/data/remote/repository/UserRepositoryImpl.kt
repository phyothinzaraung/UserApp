package dev.phyo.userapp.data.remote.repository

import dev.phyo.userapp.data.model.User
import dev.phyo.userapp.data.remote.UserHelper
import dev.phyo.userapp.domain.repository.IUserRepository
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val userHelper: UserHelper): IUserRepository {
    override suspend fun getUsers(): Flow<DataResult<List<User>>> {
        return flow<DataResult<List<User>>> {
            emit(DataResult.Loading())
            with(userHelper.getUsers()){
                if (isSuccessful) {
                    emit(DataResult.Success(this.body()?.data ?: emptyList()))
                }else{
                    emit(DataResult.Error(this.message().orEmpty()))
                }
            }
        }.catch {
            emit(DataResult.Error(it.message))
        }
    }
}