package com.example.myapplication.data.remote.repository

import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.response.LoginResponse
import com.example.myapplication.data.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(email: String, password: String) : Flow<Result<LoginResponse>>

    fun register(name: String, username: String, email: String, password: String, role: String) : Flow<Result<RegisterResponse>>
}