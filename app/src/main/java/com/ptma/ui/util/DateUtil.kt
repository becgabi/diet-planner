package com.ptma.ui.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    private const val DATE_FORMAT = "yyyy. MM. dd."
    private const val TIME_FORMAT = "HH:mm"
    private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    private val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT)

    @JvmStatic
    fun getDateFormat(dateTime: LocalDateTime?): String {
        return DATE_FORMATTER.format(dateTime)
    }

    @JvmStatic
    fun getTimeFormat(dateTime: LocalDateTime?): String {
        return TIME_FORMATTER.format(dateTime)
    }
}