package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class NameResponse(
    @field:SerializedName("title") val title: String = "",
    @field:SerializedName("first") val first: String = "",
    @field:SerializedName("last") val last: String = ""
)