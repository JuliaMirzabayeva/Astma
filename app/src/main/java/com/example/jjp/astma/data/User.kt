package com.example.jjp.astma.data

import java.util.*

class User(val id: Int,
           val name: String,
           val surname: String,
           val sex: Int,
           val birth: Date,
           val height: Int,
           val weight: Int)

enum class UserSex(val value: Int) {
    WOMAN(0),
    MAN(1),
    OTHER(2)
}

