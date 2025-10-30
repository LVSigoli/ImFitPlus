package com.lucas.sigoli.sc3020428.imfitplus.enums

enum class UserErrors(val message: String) {
    NAME_EMPTY("O Nome deve ser preenchido"),

    AGE_INVALID("A Idade deve ser preenchida e maior que zero"),

    HEIGHT_INVALID("A Altura deve ser preenchida e maior que zero"),

    WEIGHT_INVALID("O Peso deve ser preenchido e maior que zero"),

    GENDER_INVALID("O Sexo deve ser informado")
}