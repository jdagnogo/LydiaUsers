package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class LocationResponse (
    @field:SerializedName("street") val street: String = "",
    @field:SerializedName("city") val city: String = "",
    @field:SerializedName("state") val state: String = ""
)