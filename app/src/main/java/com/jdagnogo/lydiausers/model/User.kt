package com.jdagnogo.lydiausers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @field:SerializedName("id") val id: Long = 0L,
    @field:SerializedName("name") val name: String = "",
    @field:SerializedName("gender") val gender: String = "",
    @field:SerializedName("nationality") val nationality: String = "",
    @field:SerializedName("phone") val phone: String = "",
    @field:SerializedName("image") val image: String = "",
    @field:SerializedName("location") val location: String = "",
    @PrimaryKey @field:SerializedName("email") val email: String = ""
):Serializable