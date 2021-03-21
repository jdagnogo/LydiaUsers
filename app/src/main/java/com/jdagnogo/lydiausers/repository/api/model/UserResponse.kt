package com.jdagnogo.lydiausers.repository.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("gender") val gender: String = "",
    @field:SerializedName("phone") val phone: String = "",
    @field:SerializedName("cell") val cell: String = "",
    @field:SerializedName("nat") val nationality: String = "",
    @field:SerializedName("registered") val registered: Int = 0,
    @field:SerializedName("dob") val dob: Int = 0,
    @field:SerializedName("name") val name: NameResponse? = null,
    @field:SerializedName("picture") val picture: PictureResponse? = null,
    @field:SerializedName("id") val idResponse: IdResponse? = null,
    @field:SerializedName("location") val locationResponse: LocationResponse? = null,
    @field:SerializedName("login") val loginResponse: LoginResponse? = null,
    @field:SerializedName("email") val email: String = ""
)