package com.mlievens.adventofcode2025

import kotlin.text.substring


fun main() {
    val input = readInput("test")
    println(Day3.part2(input))
}

object Day3  {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            sum += findVoltageForBank(it)
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        var sum = 0L
        input.forEach {
            sum += outputJoltage(it, 12)
        }
        return sum
    }

    fun findVoltageForBank(bank: String): Int {
        var highestIndex = -1
        var nextIndex = -1

        var highestValue = -1
        bank.forEachIndexed { index, digit ->
            val joltage = digit.digitToInt()
            if (joltage > highestValue && index < bank.lastIndex) {
                highestValue = joltage
                highestIndex = index
            }
        }
        highestValue = -1
        val subBank = bank.substring(highestIndex+1)
        subBank.forEachIndexed { index, digit ->
            val joltage = digit.digitToInt()
            if (joltage > highestValue) {
                highestValue = joltage
                nextIndex = index
            }
        }
        val result = "${bank[highestIndex]}${subBank[nextIndex]}"
        println(result)
        return result.toInt()
    }

    fun outputJoltage(bank: String, numDigits: Int): Long {
        var checkableBank = bank
        return buildString {
            for (digit in (0 until numDigits)) {
                // Find the largest battery that could be part of the result (front of the list).
                val max = checkableBank.dropLast(numDigits - digit - 1).maxBy { it.digitToInt() }
                append(max)
                // Drop everything before (and including) this battery, and check for the next digit.
                checkableBank = checkableBank.drop(checkableBank.indexOf(max) + 1)
            }
        }.toLong()
    }
}
