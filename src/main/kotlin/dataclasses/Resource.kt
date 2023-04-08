package dataclasses

import com.google.gson.annotations.SerializedName

data class Resource(
    @SerializedName("data") val data: ResourceData,
    @SerializedName("support") val support: Support,
) {
    data class ResourceData(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("year") val year: Int,
        @SerializedName("color") val color: String,
        @SerializedName("pantone_value") val pantoneValue: String
    )
}
