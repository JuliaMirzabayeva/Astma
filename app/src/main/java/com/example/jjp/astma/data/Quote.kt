package com.example.jjp.astma.data

class Quote(var value: Long, var date: QuoteDate, var dayPeriod: DayPeriod) {

    enum class DayPeriod {
        MORNING,
        EVENING
    }
}