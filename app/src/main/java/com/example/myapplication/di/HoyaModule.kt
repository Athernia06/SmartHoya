package com.example.myapplication.di

import com.example.myapplication.common.repository.HoyaRepositoryImp
import com.example.myapplication.data.remote.network.HoyaApi
import com.example.myapplication.data.remote.repository.HoyaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class HoyaModule {

    @Singleton
    @Provides
    fun provideHoyaApi(retrofit: Retrofit) : HoyaApi {
        return retrofit.create(HoyaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHoyaRepository(hoyaApi: HoyaApi) : HoyaRepository {
        return HoyaRepositoryImp(hoyaApi)
    }
}