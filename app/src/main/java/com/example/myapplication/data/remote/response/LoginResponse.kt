package com.example.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)

data class User(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("foto")
	val foto: Any,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("remember_token")
	val rememberToken: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
