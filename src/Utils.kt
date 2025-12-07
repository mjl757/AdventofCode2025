package com.mlievens.adventofcode2025

import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/inputs/$name.txt").readText().trim().lines()