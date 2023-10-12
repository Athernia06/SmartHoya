package com.example.myapplication.common.repository

import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.network.HoyaApi
import com.example.myapplication.data.remote.repository.HoyaRepository
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.data.remote.response.IslandResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HoyaRepositoryImp @Inject constructor(private val hoyaApi: HoyaApi) : HoyaRepository {
    override fun listIsland(token: String): Flow<Result<List<IslandResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = hoyaApi.island(token)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override fun listHoya(token: String, islandId: String): Flow<Result<List<HoyaResponse>>> {
        return flow {
            emit(Result.Loading)
            try {
                val result = hoyaApi.listHoya(token, islandId)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

}