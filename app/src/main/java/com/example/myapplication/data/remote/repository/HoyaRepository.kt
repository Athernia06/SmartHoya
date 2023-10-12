package com.example.myapplication.data.remote.repository

import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.data.remote.response.IslandResponse
import kotlinx.coroutines.flow.Flow

interface HoyaRepository {

    fun listIsland(token: String) : Flow<Result<List<IslandResponse>>>

    fun listHoya(token: String, islandId: String) : Flow<Result<List<HoyaResponse>>>
}