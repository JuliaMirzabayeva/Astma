package com.example.jjp.astma.data

import java.util.*

class User(val token: String,
           val id: Int,
           val name: String,
           val surname: String,
           val sex: Int,
           val birthDate: Date,
           val height: Int,
           val weight: Int,
           val peakFlowmetryBounds: PeakFlowmetryBounds)

enum class UserSex(val value: Int) {
    WOMAN(0),
    MAN(1),
    OTHER(2)
}

