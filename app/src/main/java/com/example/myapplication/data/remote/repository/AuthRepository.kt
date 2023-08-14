package com.example.myapplication.data.remote.repository

import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(email: String, password: String) : Flow<Result<LoginResponse>>
}