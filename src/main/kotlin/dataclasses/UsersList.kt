package dataclasses

import com.google.gson.annotations.SerializedName

data class UsersList(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<User.UserData>,
    @SerializedName("support") val support: Support
)
