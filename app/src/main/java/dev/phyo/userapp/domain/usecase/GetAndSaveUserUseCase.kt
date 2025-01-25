package dev.phyo.userapp.domain.usecase

import dev.phyo.userapp.domain.repository.IUserRepository
import javax.inject.Inject

class GetAndSaveUserUseCase @Inject constructor(private val userRepository: IUserRepository) {

    suspend operator fun invoke(){
        userRepository.getAndSaveUser()
    }
}