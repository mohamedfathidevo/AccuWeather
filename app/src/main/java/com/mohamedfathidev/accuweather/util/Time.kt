package com.mohamedfathidev.accuweather.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun parseDateToTime(time: String): String {
    val inputSDF = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())
    val outputSDF = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val date: Date? = try {
        inputSDF.parse(time)
    } catch (e: ParseException) {
        return time
    }
    return outputSDF.format(date)

}

fun parseDateToDay(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("EEE, dd MMM")
    val date: Date? = try {
        inputFormat.parse(dateString)
    } catch (e: ParseException) {
        return dateString
    }
    return outputFormat.format(date)
}