package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class UserResults (
    @field:SerializedName("gender") val gender: String = "",
    @field:SerializedName("name") val name: Name? = null
)