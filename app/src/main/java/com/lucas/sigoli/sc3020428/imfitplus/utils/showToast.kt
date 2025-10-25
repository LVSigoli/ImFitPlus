package com.lucas.sigoli.sc3020428.imfitplus.utils

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(message: String, duration: Int) {
    return Toast.makeText(this, message, duration).show()
}