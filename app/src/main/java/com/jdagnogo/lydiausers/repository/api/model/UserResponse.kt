package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("gender") val gender: String = "",
    @field:SerializedName("name") val name: NameResponse? = null,
    @field:SerializedName("id") val idResponse: IdResponse? = null,
    @field:SerializedName("email") val email: String = ""
)