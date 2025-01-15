package com.example.fitapp.domain.usecases

import com.example.fitapp.data.remote.firebase.repository.AuthRepository
import com.example.fitapp.domain.UserModel
import kotlinx.coroutines.flow.Flow

class CreateUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Result<UserModel>> {
        return authRepository.createUser(email, password)
    }
}