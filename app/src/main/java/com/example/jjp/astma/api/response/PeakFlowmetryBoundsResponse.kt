package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName

data class PeakFlowmetryBoundsResponse(@SerializedName("topBound") val topBound: Double,
                                       @SerializedName("bottomBound") val bottomBound: Double,
                                       @SerializedName("userRate") val userRate: Double)