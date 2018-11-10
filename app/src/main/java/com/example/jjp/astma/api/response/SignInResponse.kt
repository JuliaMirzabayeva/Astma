package com.example.jjp.astma.api.response

import com.example.jjp.astma.data.User
import com.google.gson.annotations.SerializedName;


data class SignInResponse(@SerializedName("token") val token: String,
                          @SerializedName("user") val user: User)