package dataclasses

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data") val data: UserData,
    @SerializedName("support") val support: Support,
) {
    data class UserData(
        @SerializedName("id") val id: Int,
        @SerializedName("email") val email: String,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("avatar") val avatar: String
    )
}
