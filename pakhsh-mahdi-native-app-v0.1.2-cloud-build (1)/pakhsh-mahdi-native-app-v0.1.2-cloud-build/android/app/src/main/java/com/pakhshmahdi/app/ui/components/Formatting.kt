package com.pakhshmahdi.app.ui.components

import java.text.NumberFormat
import java.util.Locale

fun toman(value: String): String {
    val number = value.toDoubleOrNull() ?: 0.0
    return NumberFormat.getNumberInstance(Locale.US).format(number.toLong()) + " تومان"
}
