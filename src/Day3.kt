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
            sum += findBigVoltageForBank(it)
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

    fun findBigVoltageForBank(bank: String): Long {
        val indicies = mutableListOf<Int>()

        for (i in 0..10) {
            var highestValue = -1
            var highestIndex = -1
            bank.forEachIndexed { index, digit ->
                val joltage = digit.digitToInt()
                if (joltage >= highestValue && !indicies.contains(index)) {
                    highestValue = joltage
                    highestIndex = index
                }
            }
            indicies.add(highestIndex)
        }
        indicies.sort()


        var result = ""
        indicies.forEach {
            result += bank[it]
        }
        println(result)
        return result.toLong()
    }



}
