package util.core

import kotlin.io.path.Path
import kotlin.io.path.readText

const val LOCAL_DATA_DIR = "input"

/**
 * Abstract template for an AoC solution.
 *
 * @param T the return type of the part 1 solution
 * @param U the return type of the part 2 solution
 */
abstract class Solution<T, U>(val year: Int, val day: Int) {
    val input by lazy { readInput(year, day) }

    abstract fun part1(input: String): T
    abstract fun part2(input: String): U
}

/**
 * Reads the AoC input file for the given day in the given year.
 *
 * @param year the year of the AoC puzzle
 * @param day the day of the AoC puzzle
 */
fun readInput(year: Int, day: Int) = Path("$LOCAL_DATA_DIR/year$year/Day${day.toString().padStart(2, '0')}.txt").readText().trimEnd()

/**
 * Returns 6th *element* from the list.
 *
 * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
 */
operator fun <T> List<T>.component6() = get(5)

/**
 * Returns 7th *element* from the list.
 *
 * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
 */
operator fun <T> List<T>.component7() = get(6)

/**
 * Represents a 4D vector of 32-bit signed integers.
 */
data class Int4(val x: Int, val y: Int, val z: Int, val w: Int)
