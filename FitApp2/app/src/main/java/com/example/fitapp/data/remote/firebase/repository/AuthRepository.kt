package com.example.fitapp.data.remote.firebase.repository

import com.example.fitapp.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
   suspend fun login(email: String, password: String): Flow<Result<UserModel>>
   suspend fun createUser(email: String, password: String): Flow<Result<UserModel>>
}