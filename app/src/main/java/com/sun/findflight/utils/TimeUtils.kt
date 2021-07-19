package com.sun.findflight.utils

object TimeUtils {

    fun updateTokenTime(tokenTime: Long?) =
        tokenTime?.let { System.currentTimeMillis() / BaseConst.SECONDS_VALUE + it }

    fun tokenExpired(tokenTime: Long?) =
        tokenTime?.let { it > System.currentTimeMillis() / BaseConst.SECONDS_VALUE } ?: true
}
