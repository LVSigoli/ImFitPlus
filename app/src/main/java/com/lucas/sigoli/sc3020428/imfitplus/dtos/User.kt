package com.lucas.sigoli.sc3020428.imfitplus.dtos


import android.os.Parcelable
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.enums.SportsLevel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val age: Int = 0,
    val name: String = "",
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val gender: Gender = Gender.NAO_INFORMADO,
    val sportsLevel: SportsLevel = SportsLevel.SEDENTARIO
): Parcelable
