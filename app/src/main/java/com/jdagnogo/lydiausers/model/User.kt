package com.jdagnogo.lydiausers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @field:SerializedName("id") val id: Long = 0L,
    @field:SerializedName("first") val first: String = "",
    @field:SerializedName("last") val last: String = "",
    @PrimaryKey @field:SerializedName("email") val email: String = ""
)