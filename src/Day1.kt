package com.mlievens.adventofcode2025

import kotlin.math.absoluteValue

fun main() {
    val input = readInput("Day01")
    Day1.getNumberOfTimesDialPassesZero(input)
}

object Day1 {
    fun getNumberOfTimesDialLandsOnZero(input: List<String>) {
        var total = 0
        var currentPosition = 50
        input.forEach {
            currentPosition = turnDial(it, currentPosition).first
            if (currentPosition == 0) {
                total++
            }
            println(currentPosition)
        }
        println("total = $total")
    }

    fun turnDial(turn: String, currentPosition: Int): Pair<Int, Int> {
        println("Turn = $turn")
        val direction = turn.first()
        val distance = turn.substring(1).toInt()
        return when (direction) {
            'L' -> minus(currentPosition, distance)
            'R' -> add(currentPosition, distance)
            else -> currentPosition to 0
        }
    }

    fun add(a: Int, b: Int): Pair<Int, Int> {
        val sum = a + b
        val timesAround0 = sum / 100
        val result = sum % 100

        return result to timesAround0
    }

    fun minus(a: Int, b: Int): Pair<Int, Int> {
        val diff = a - b
        var timesAround0 = (diff / 100).absoluteValue
        val result = (diff % 100 + 100) % 100
        if (diff % 100 < 0 && a != 0) {
            timesAround0++
        }
        if (result == 0) {
            timesAround0++
        }
        return result to timesAround0
    }

    fun getNumberOfTimesDialPassesZero(input: List<String>) {
        var total = 0
        var currentPosition = 50
        println("Start Position = $currentPosition")
        println("================")
        input.forEach {
            val pair = turnDial(it, currentPosition)
            currentPosition = pair.first
            total += pair.second
            println("Current Position = $currentPosition")
            println("Total Times = $total")
            println("================")
        }
        println(currentPosition)
        println("Total = $total")
    }
}
//fun add(a: Int, b: Int): Int {
//    val sum = a + b
//    return if (sum <= 99) {
//        sum
//    } else {
//        0 + (sum - 99)
//    }
//}
//
//fun minus(a: Int, b: Int): Int {
//    val sum = a - b
//    return if (sum >= 0) {
//        sum
//    } else {
//        99 + sum
//    }
//}