package dev.phyo.userapp.domain.usecase

import dev.phyo.userapp.data.model.User
import dev.phyo.userapp.domain.repository.IUserRepository
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: IUserRepository) {

    suspend operator fun invoke(): Flow<DataResult<List<User>>>{
        return flow {
            userRepository.getUsers().collect{
                when(it){
                    is DataResult.Success -> {
                        emit(DataResult.Success(it.data))
                    }
                    is DataResult.Error -> {
                        emit(DataResult.Error(it.message))
                    }
                    is DataResult.Loading -> {
                        emit(DataResult.Loading())
                    }
                }
            }
        }
    }
}