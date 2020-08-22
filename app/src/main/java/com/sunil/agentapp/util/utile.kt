package com.sunil.assignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*


const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_FORMAT = "dd MMM yyyy"

fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}


fun getDateFromUtc(date: String): String {

    val utcDateFormat = SimpleDateFormat(UTC_FORMAT, Locale.getDefault())
    utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")

    val utcDate = utcDateFormat.parse(date)


    val localDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    localDateFormat.timeZone = TimeZone.getDefault()

    return localDateFormat.format(utcDate)
}