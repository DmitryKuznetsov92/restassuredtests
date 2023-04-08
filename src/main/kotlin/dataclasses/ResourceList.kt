package dataclasses

import com.google.gson.annotations.SerializedName

data class ResourceList (
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<Resource.ResourceData>,
    @SerializedName("support") val support: Support
)