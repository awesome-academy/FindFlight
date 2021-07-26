package com.sun.findflight.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private const val TIME_FORMAT = "yyyy-MM-dd"

    fun updateTokenTime(tokenTime: Long?) =
        tokenTime?.let { System.currentTimeMillis() / BaseConst.SECONDS_VALUE + it }

    fun tokenExpired(tokenTime: Long?) =
        tokenTime?.let { it > System.currentTimeMillis() / BaseConst.SECONDS_VALUE } ?: true

    fun convertToDate(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return simpleDateFormat.format(calendar.time)
    }
}
