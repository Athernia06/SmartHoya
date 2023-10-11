package com.example.myapplication.di

import com.example.myapplication.common.repository.AuthRepositoryImpl
import com.example.myapplication.data.remote.network.AuthApi
import com.example.myapplication.data.remote.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authApi: AuthApi) : AuthRepository {
        return AuthRepositoryImpl(authApi)
    }
}