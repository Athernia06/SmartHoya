package com.example.myapplication.data.remote.network

import com.example.myapplication.data.remote.response.ForumResponse
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.data.remote.response.IslandResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HoyaApi {

    @GET("pulau")
    suspend fun island(@Header("Authorization") token: String): List<IslandResponse>

    @GET("pulau/tanaman")
    suspend fun listHoya(
        @Header("Authorization") token: String,
        @Query("id_pulau") islandId: String
    ): List<HoyaResponse>

    @GET("forums")
    suspend fun listForum(@Header("Authorization") token: String): List<ForumResponse>
}