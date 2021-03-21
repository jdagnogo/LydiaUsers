package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @field:SerializedName("large") val large: String = "",
    @field:SerializedName("medium") val medium: String = "",
    @field:SerializedName("thumbnail") val thumbnail: String = ""
)