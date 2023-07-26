package com.example.myapplication.core.util.wrapper

import com.google.gson.annotations.SerializedName

data class GeminiResponseWrapper<T>(
    @SerializedName("entity") var entity: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("meta") var meta: Meta? = Meta(),
    @SerializedName("data") var data: T? = null
) {
    data class Meta(
        @SerializedName("per_page") var perPage: Int? = null,
        @SerializedName("page") var page: Int? = null,
        @SerializedName("total") var total: Int? = null,
        @SerializedName("order_by") var orderBy: String? = null,
        @SerializedName("order_type") var orderType: String? = null
    )
}