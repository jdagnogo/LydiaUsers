package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("username") val username: String = "",
    @field:SerializedName("password") val password: String = "",
    @field:SerializedName("salt") val salt: String = "",
    @field:SerializedName("md5") val md5: String = "",
    @field:SerializedName("sha1") val sha1: String = "",
    @field:SerializedName("sha256") val sha256: String = ""
)