package com.example.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class HoyaResponse(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String?,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("id_pulau")
	val idPulau: Int
)