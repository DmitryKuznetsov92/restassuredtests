package dataclasses

import com.google.gson.annotations.SerializedName

data class LogResponse(
    @SerializedName("token") val token: String,
)
