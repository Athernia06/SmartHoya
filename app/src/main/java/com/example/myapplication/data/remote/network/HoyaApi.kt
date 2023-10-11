package com.example.myapplication.data.remote.network

import com.example.myapplication.data.remote.response.IslandResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface HoyaApi {

    @GET("pulau")
    suspend fun island(@Header("Authorization") token: String): List<IslandResponse>
}