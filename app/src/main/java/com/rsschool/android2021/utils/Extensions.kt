package com.rsschool.android2021.utils

import android.content.Context
import android.widget.Toast
import com.rsschool.android2021.R

fun String.isItInt(context: Context): Boolean =
    try {
        toInt()
        true
    } catch (e: NumberFormatException) {
        Toast.makeText(
            context,
            context.getString(R.string.max_allowed, Int.MAX_VALUE),
            Toast.LENGTH_LONG
        ).show()
        false
    }