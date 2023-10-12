package com.example.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class IslandResponse(

	@field:SerializedName("nama_pulau")
	val namaPulau: String,

	@field:SerializedName("id")
	val id: Int
)
