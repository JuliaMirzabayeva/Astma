package com.example.jjp.astma.api.request

import java.util.*

class SignUpRequest(val login: String,
                    val password: String,
                    val name: String,
                    val surname: String,
                    val sex: Int,
                    val birthDate: Date,
                    val height: Int,
                    val weight: Int)