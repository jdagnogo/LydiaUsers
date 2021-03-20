package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class IdResponse(
    @field:SerializedName("name") val name: String = "",
    @field:SerializedName("value") val value: String = ""
)