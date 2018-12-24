package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class QuoteResponse(@SerializedName("id") val id: Int,
                         @SerializedName("value") val value: Int,
                         @SerializedName("date") val date: Date,
                         @SerializedName("isMorning") val isMorning: Boolean)