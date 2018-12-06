package com.example.jjp.astma.api.request

import java.util.Date

class QuoteRequest(val value: Int, val date: Date, val isMorning: Boolean, token : String) : BaseRequest(token)