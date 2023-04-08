package dataclasses

import com.google.gson.annotations.SerializedName

data class LogRegRequest(
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
)
