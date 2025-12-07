package com.mlievens.adventofcode2025

import com.soberg.aoc.utlities.datastructures.Grid2D
import com.soberg.aoc.utlities.datastructures.toCharGrid2D


fun main() {
    val input = readInput("Day4")
    val grid: Grid2D<Char> = input.toCharGrid2D()
    println(Day4.part2(grid))
}

object Day4 {

    fun part1(input: Grid2D<Char>): Int {
        var numberOfAccessibleRolls = 0
        input.traverse { location, element ->
            if (element == '@' && input.numberOfRollsAroundRoll(location) < 4) {
                numberOfAccessibleRolls++
            }
        }
        return numberOfAccessibleRolls
    }

    fun part2(input: Grid2D<Char>): Int {
        var totalRollsRemoved = 0
        var result: Pair<Grid2D<Char>, Int> = removeRolls(input)
        while (result.second > 0) {
            totalRollsRemoved += result.second
            result = removeRolls(result.first)
        }
        return totalRollsRemoved
    }

    fun removeRolls(grid: Grid2D<Char>): Pair<Grid2D<Char>, Int> {
        var numberOfAccessibleRolls = 0
        var reducedGrid = grid
        grid.traverse { location, element ->
            if (element == '@' && grid.numberOfRollsAroundRoll(location) < 4) {
                numberOfAccessibleRolls++
                reducedGrid = reducedGrid.replace(location, '.')
            }
        }
        return reducedGrid to numberOfAccessibleRolls
    }

    fun Grid2D<Char>.numberOfRollsAroundRoll(location: Grid2D.Location): Int {
        var numberOfRolls = 0
        for (direction in Grid2D.Direction.entries) {
            val locationToCheck = location.move(direction)
            if (this.isInBounds(locationToCheck) && this.get(locationToCheck) == '@') {
                numberOfRolls++
            }
        }
        return numberOfRolls
    }
}