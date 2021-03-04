package com.jovandy.testrs.api.respone.deletedata

import com.google.gson.annotations.SerializedName

data class ResponseDeleteData(

	@field:SerializedName("author_url")
	val authorUrl: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("version")
	val version: String? = null
)

data class Data(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)
