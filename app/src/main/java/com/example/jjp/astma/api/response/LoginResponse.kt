package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName;


data class LoginResponse(@SerializedName("token") val token: String,
                         @SerializedName("user") val user:  UserResponse,
                         @SerializedName("peakFlowmetryBounds") val peakFlowmetryBound: PeakFlowmetryBoundsResponse)