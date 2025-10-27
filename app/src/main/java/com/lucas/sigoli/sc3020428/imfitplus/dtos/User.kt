package com.lucas.sigoli.sc3020428.imfitplus.dtos

// External Libraries
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Utils
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel

@Parcelize
data class User(
    val age: Int = 0,
    val name: String = "",
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val gender: Gender = Gender.NAO_INFORMADO,
    val sportsLevel: SportsLevel = SportsLevel.SEDENTARIO,
    var imc : String = "0,00",
    var caloriesSpent:String = "0,00",
    val baseCalories: String = "0,00",
    val idealWeight: String = "0,00"

): Parcelable
