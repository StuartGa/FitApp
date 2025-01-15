package com.example.fitapp.data.remote.firebase.repository

import com.example.fitapp.domain.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    
    override suspend fun login(email: String, password: String): Flow<Result<UserModel>> = flow {
        emit(
            Result.success(
                UserModel(
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .await().user?.email.toString(),
                    userEmail = ""
                )
            )
        )
    }.catch { e ->
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                emit(Result.failure(e))
            }

            else -> {
                emit(Result.failure(e))
            }
        }
    }


    override suspend fun createUser(email: String, password: String): Flow<Result<UserModel>> = flow {
        emit(
            Result.success(
                UserModel(
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .await().user?.email.toString(),
                    userEmail = ""
                )
            )
        )
    }.catch { e ->
        emit(Result.failure(e))
    }
}