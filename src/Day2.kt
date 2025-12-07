package com.mlievens.adventofcode2025

import kotlin.text.substring


fun main() {
    val input = readInput("Day02")
    val ranges = input.first().split(",")
    println(Day2.part2(ranges))
}

object Day2  {

    fun part1(input: List<String>): Long {
        println(input)
        var sum = 0L
        input.forEach { range ->
            sum += getInvalidIdSumForRange(range)
            println("Current total sum: $sum")
            println("====================")
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        println(input)
        var sum = 0L
        input.forEach { range ->
            sum += getVeryInvalidIdSumForRange(range)
            println("Current total sum: $sum")
            println("====================")
        }
        return sum
    }

    private fun getInvalidIdSumForRange(range: String): Long {
        val ranges: List<Long> = range.split("-").map { it.toLong() }
        val min = ranges[0]
        val max = ranges[1]
        var sum = 0L
        println("Range: $range")
        for (i in min..max) {
            if (!i.isValidId()) {
                sum += i
                println("Invalid: $i")
                println("Current sum for range: $sum")
            }
        }
        return sum
    }

    private fun Long.isValidId(): Boolean {
        val asString = this.toString()
        if (asString.length % 2 == 1) return true
        val midPoint = asString.length / 2
        val firstHalf = asString.take(midPoint)
        val secondHalf = asString.substring(midPoint)
        return firstHalf != secondHalf
    }

    private fun getVeryInvalidIdSumForRange(range: String): Long {
        val ranges: List<Long> = range.split("-").map { it.toLong() }
        val min = ranges[0]
        val max = ranges[1]
        var sum = 0L
        println("Range: $range")
        for (i in min..max) {
            if (i.isInValidId()) {
                sum += i
                println("Invalid: $i")
                println("Current sum for range: $sum")
            }
        }
        return sum
    }

    private fun Long.isInValidId(): Boolean {
        val asString = this.toString()
        val len = asString.length
        if (len < 2) return false // A single character cannot be a repeating sequence

        // Iterate through all possible lengths of a repeating subsequence
        for (subLen in 1..len / 2) {
            // Check if the total length is a multiple of the subsequence length
            if (len % subLen == 0) {
                val substring = asString.substring(0, subLen)
                val repeats = len / subLen
                // Build a string by repeating the subsequence
                val repeatedString = substring.repeat(repeats)
                // If it matches the original string, we found a repeating sequence
                if (asString == repeatedString) {
                    return true
                }
            }
        }
        // If no repeating subsequence was found
        return false
    }
}
