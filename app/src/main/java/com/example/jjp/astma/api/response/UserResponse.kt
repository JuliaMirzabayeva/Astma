package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserResponse(@SerializedName("id") val id: Int,
                        @SerializedName("name") val name: String,
                        @SerializedName("surname") val surname: String,
                        @SerializedName("sex") val sex: Int,
                        @SerializedName("birthDate") val birth: Date,
                        @SerializedName("height") val height: Int,
                        @SerializedName("weight") val weight: Int)