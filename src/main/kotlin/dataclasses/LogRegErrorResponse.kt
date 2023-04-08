package dataclasses

import com.google.gson.annotations.SerializedName

data class LogRegErrorResponse(
    @SerializedName("error") val error: String
)
