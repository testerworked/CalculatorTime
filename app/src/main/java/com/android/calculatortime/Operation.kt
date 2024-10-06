package com.android.calculatortime

class Operation(private val time1: String,private val time2: String) {


    fun updateTimeFormat(duration: String): String {
        return duration.replace("h", "h ").replace("m", "m ").replace("s", "s ")
            .trim()
    }

    fun parseTimeStr(time: String): Int {
        var totalSeconds = 0
        val parts = time.split(" ")
        for (part in parts) {
            val value = part.substring(0, part.length - 1).toInt()
            val unit = part.substring(part.length - 1)
            when (unit) {
                'h'.toString() -> totalSeconds += value * 3600
                'm'.toString() -> totalSeconds += value * 60
                's'.toString() -> totalSeconds += value
            }
        }
        return totalSeconds
    }

    fun checkFormatTime(totalSeconds: Int): String {
        var hours = totalSeconds / 3600
        var minutes = (totalSeconds % 3600) / 60
        var seconds = totalSeconds % 60

        val timeParts = mutableListOf<String>()
        if (hours > 0) timeParts.add("$hours"+"h")
        if (minutes > 0) timeParts.add("$minutes"+"m")
        if (seconds > 0) timeParts.add("$seconds"+"s")

        return timeParts.joinToString(" ")
    }
    fun sum(): String {
        val totalSec1 = parseTimeStr(updateTimeFormat(time1))
        val totalSec2 = parseTimeStr(updateTimeFormat(time2))

        val totalSeconds = totalSec1 + totalSec2

        return checkFormatTime(totalSeconds)
    }

    fun dif(): String {
        val totalSec1 = parseTimeStr(updateTimeFormat(time1))
        val totalSec2 = parseTimeStr(updateTimeFormat(time2))
        var totalSeconds = 0
        if(totalSec1 >= totalSec2){
            totalSeconds = totalSec1 - totalSec2
        }else{
            totalSeconds = totalSec2 - totalSec1
        }

        return checkFormatTime(totalSeconds)
    }
}