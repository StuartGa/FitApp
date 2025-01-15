package com.example.fitapp.data.remote.firebase.di.auth

import com.example.fitapp.data.remote.firebase.repository.AuthRepository
import com.example.fitapp.data.remote.firebase.repository.AuthRepositoryImpl
import com.example.fitapp.domain.usecases.CreateUserUseCase
import com.example.fitapp.domain.usecases.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    fun providesLoginUseCase(authRepository: AuthRepository): LoginUseCase = LoginUseCase(authRepository)

    @Provides
    fun providesSignUpUseCase(authRepository: AuthRepository): CreateUserUseCase = CreateUserUseCase(authRepository)
}