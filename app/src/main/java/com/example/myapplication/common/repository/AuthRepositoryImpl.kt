package com.example.myapplication.common.repository

import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.network.auth.AuthApi
import com.example.myapplication.data.remote.repository.AuthRepository
import com.example.myapplication.data.remote.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override fun login(email: String, password: String): Flow<Result<LoginResponse>> = flow {
        emit(Result.Loading)
        try {
            val result = authApi.login(email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}