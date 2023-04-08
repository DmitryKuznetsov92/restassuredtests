package dataclasses

import com.google.gson.annotations.SerializedName

data class RegResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("token") val token: String?,
)
