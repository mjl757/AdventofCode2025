package com.mlievens.adventofcode2025

import kotlin.math.min

fun main() {
    val input = readInput("Day5")
    println(input)
    println(Day5.part2(input))
}

object Day5 {

    fun part1(input: List<String>): Int {
        var sum = 0
        val ranges = input.parseRanges()
        val ids = input.parseIds()
        println(ranges)
        println(ids)
        for (id in ids) {
            var isGood = false
            for (range in ranges) {
                if (range.contains(id)) {
                    isGood = true
                    continue
                }
            }
            if (isGood) {
                sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val ranges = input.parseRanges()
        val combinedRanges = combineOverlappingRanges(ranges)
        var totalIds = 0L
        for (range in combinedRanges) {
            totalIds += (range.last - range.first) + 1
        }
        return totalIds.toInt()

    }

    fun combineOverlappingRanges(ranges: List<LongRange>): List<LongRange> {
        if (ranges.isEmpty()) {
            return emptyList()
        }
        val sortedRanges = ranges.sortedBy { it.first }
        val mergedRanges = mutableListOf<LongRange>()
        var currentMerge = sortedRanges.first()
        for (range in sortedRanges) {
            if (range.first <= currentMerge.last && range.last >= currentMerge.first) {
                currentMerge = LongRange(minOf(currentMerge.first, range.first), maxOf(currentMerge.last, range.last))
            } else {
                mergedRanges.add(currentMerge)
                currentMerge = range
            }
        }
        mergedRanges.add(currentMerge)
        return mergedRanges
    }

    fun List<String>.parseRanges(): List<LongRange> {
        val split = this.indexOf("")
        val rangeList: MutableList<LongRange> = mutableListOf()
        this.forEachIndexed { index, rangeStr ->
            if (index >= split) return@forEachIndexed
            val range = rangeStr.split("-").map { it.toLong() }
            rangeList.add(LongRange(range[0], range[1]))
        }
        return rangeList
    }

    fun List<String>.parseIds(): List<Long> {
        val split = this.indexOf("")
        val idList = this.subList(split + 1, this.lastIndex + 1)
        return idList.map { it.toLong() }
    }
}