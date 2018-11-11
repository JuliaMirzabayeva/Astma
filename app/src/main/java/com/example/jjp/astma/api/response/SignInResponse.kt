package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName;


data class SignInResponse(@SerializedName("token") val token: String)