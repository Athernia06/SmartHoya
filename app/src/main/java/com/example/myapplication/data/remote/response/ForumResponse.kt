package com.example.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class ForumResponse(

	@field:SerializedName("comment_count")
	val commentCount: Int?,

	@field:SerializedName("share_count")
	val shareCount: Int?,

	@field:SerializedName("like_count")
	val likeCount: Int?,

	@field:SerializedName("foto")
	val foto: String?,

	@field:SerializedName("bookmark_count")
	val bookmarkCount: Int?,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("user")
	val user: UserResponse
)

data class UserResponse(

	@field:SerializedName("foto")
	val foto: String?,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)