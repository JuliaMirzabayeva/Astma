package com.example.jjp.astma.data

class Quote {
    var value: Long? = null
    var date: Long? = null
    var dayPeriod: Quote.DayPeriod? = null

    enum class DayPeriod {
        MORNING,
        EVENING
    }
}