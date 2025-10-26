package com.lucas.sigoli.sc3020428.imfitplus.validators

//Utils
import com.lucas.sigoli.sc3020428.imfitplus.enums.Gender
import com.lucas.sigoli.sc3020428.imfitplus.enums.UserErrors

// Types
import com.lucas.sigoli.sc3020428.imfitplus.dtos.User


object UserValidator {

    fun validate(user: User): String {
        if (user.name.isBlank()) return UserErrors.NAME_EMPTY.message

        if (user.age <= 0) return UserErrors.AGE_INVALID.message

        if (user.height <= 0.0) return UserErrors.HEIGHT_INVALID.message

        if (user.weight <= 0.0) return UserErrors.WEIGHT_INVALID.message

        if(user.gender === Gender.NAO_INFORMADO) return UserErrors.GENDER_INVALID.message

        return ""
    }

}