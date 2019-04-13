package com.example.jjp.astma.models.login

import com.example.jjp.astma.api.response.LoginResponse
import com.example.jjp.astma.api.response.PeakFlowmetryBoundsResponse
import com.example.jjp.astma.data.PeakFlowmetryBounds
import com.example.jjp.astma.data.User

class LoginConverter {

    fun convertLoginResponseToUser(loginResponse: LoginResponse): User {
        return User(
                token = loginResponse.token,
                id = loginResponse.user.id,
                name = loginResponse.user.name,
                surname = loginResponse.user.surname,
                sex = loginResponse.user.sex,
                birthDate = loginResponse.user.birth,
                height = loginResponse.user.height,
                weight = loginResponse.user.weight,
                peakFlowmetryBounds = convertPeakFlowmetryBoundsResponseToPeakFlowmetryBounds(loginResponse.peakFlowmetryBound)
        )
    }

    private fun convertPeakFlowmetryBoundsResponseToPeakFlowmetryBounds(peakFlowmetryBoundsResponse: PeakFlowmetryBoundsResponse): PeakFlowmetryBounds {
        return PeakFlowmetryBounds(
                topBound = peakFlowmetryBoundsResponse.topBound,
                bottomBound = peakFlowmetryBoundsResponse.bottomBound,
                userRate = peakFlowmetryBoundsResponse.userRate
        )
    }
}