package com.maxfraire.movies.util

import android.content.res.Resources
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import timber.log.Timber

private const val DATE_FORMAT = "yyy-MM-dd"

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true

inline fun <reified T> T?.orDefault(def: T): T = this ?: def

val Int.dp: Int get() = ((this * Resources.getSystem().displayMetrics.density).toInt())

fun String.getDate(): LocalDate? {
    try {
        return LocalDate.parse(this, DateTimeFormatter.ofPattern(DATE_FORMAT))
    } catch (e: DateTimeParseException) {
        Timber.e("Error parsing date: ${e.message}")
    }
    return null
}

fun String.getYear(): String {
    return try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern(DATE_FORMAT)).year.toString()
    } catch (e: DateTimeParseException) {
        Timber.e("Error parsing date: ${e.message}")
        "-"
    }
}

fun Int?.getFormattedRuntime(): String {
    return this?.let {
        String.format("%dh %dm", it.div(60), it.rem(60))
    } ?: "-"
}

