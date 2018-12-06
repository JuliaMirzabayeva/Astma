package com.example.jjp.astma.api.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AddQuoteResponse(@SerializedName("value") val value: Int,
                            @SerializedName("date") val date: Date,
                            @SerializedName("isMorning") val isMorning: Boolean)