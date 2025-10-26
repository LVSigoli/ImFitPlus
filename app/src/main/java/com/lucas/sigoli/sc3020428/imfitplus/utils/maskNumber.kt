package com.lucas.sigoli.sc3020428.imfitplus.utils

import android.app.Activity
import android.widget.EditText
import java.util.Locale

fun Activity.maskNumber(value:String, element: EditText){
    val clean = value.replace("[^0-9]".toRegex(), "")

    if (clean.isNotEmpty()) {

        val value = clean.toDouble() / 100

        val formatted = String.format(Locale.US, "%.2f", value)


        if (formatted != value.toString()) {
           element.setText(formatted)
            element.setSelection(formatted.length)
        }
    }
}
