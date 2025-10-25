package com.lucas.sigoli.sc3020428.imfitplus.dtos

data class User(
    val age: Int = 0,
    val name: String = "",
    val gender: String = "",
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val sportsLevel: SportsLevel = SportsLevel.SEDENTARIO
)

enum class SportsLevel {
    LEVE,
    INTENSO,
    MODERADO,
    SEDENTARIO,
}