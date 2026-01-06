package util.core

import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.readText

const val LOCAL_DATA_DIR = "data"

/**
 * Abstract template for an AoC solution.
 *
 * @param T the return type of the part 1 solution
 * @param U the return type of the part 2 solution
 */
abstract class Solution<T, U>(val year: Int, val day: Int) {
    val input = readInput(year, day)
    val testInput by lazy { readTestInput(year, day) }

    abstract fun part1(input: String): T
    abstract fun part2(input: String): U
}

/**
 * Reads the AoC input file for the given day in the given year.
 *
 * @param year the year of the AoC puzzle
 * @param day the day of the AoC puzzle
 */
fun readInput(year: Int, day: Int) = Path("$LOCAL_DATA_DIR/$year/$day.txt").readText().trim()

/**
 * Reads the AoC example input which can be found in the puzzle text.
 *
 * @param year the year of the AoC puzzle
 * @param day the day of the AoC puzzle
 */
fun readTestInput(year: Int, day: Int) = Path("$LOCAL_DATA_DIR/$year/test/${day}_test.txt").readText().trim()

/**
 * Reads a specific version of AoC example input which can be found in the puzzle text.
 *
 * @param year the year of the AoC puzzle
 * @param day the day of the AoC puzzle
 * @param suffix an optional suffix, used to distinguish between multiple inputs
 */
fun readTestInput(year: Int, day: Int, suffix: String) = Path("$LOCAL_DATA_DIR/$year/test/${day}_test$suffix.txt").let {
    if (it.exists()) it.readText().trim() else readTestInput(year, day)
}

/**
 * Returns 6th *element* from the list.
 *
 * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
 */
operator fun <T> List<T>.component6() = get(5)

/**
 * Represents a 4D vector of 32-bit signed integers.
 */
data class Int4(val x: Int, val y: Int, val z: Int, val w: Int)