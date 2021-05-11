package com.partos.chess.logic.timer

class TimeFormatter() {

    fun formatNormalTime(seconds: Int): String {
        var time = ""
        when (seconds) {
            in 0..9 -> time = "0:0$seconds"
            in 10..59 -> time = "0:$seconds"
            in 60..3599 -> {
                time = (seconds / 60).toString() + ":"
                time += if (seconds % 60 <= 9) {
                    "0" + (seconds % 60).toString()
                } else {
                    (seconds % 60).toString()
                }
            }
        }
        return time
    }

    fun formatShortTime(seconds: Int): String {
        var time = "0:0" + (seconds / 1000).toString() + ":"
        when (seconds % 1000) {
            in 0..9 -> {
                time += "00" + (seconds % 1000).toString()
            }
            in 10..99 -> {
                time += "0" + (seconds % 1000).toString()
            }
            in 100..999 -> {
                time += (seconds % 1000).toString()
            }
        }
        return time
    }
}