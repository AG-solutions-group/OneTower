package com.agsolutions.td

object Utils {

    fun Float.round (decimals: Int): Float {
        var multiplier = 1.0f
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

    fun divisible(a: Int, b: Int): Boolean {
        var x = false
        if (a % b == 0) x = true

        return x
    }

}