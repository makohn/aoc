package util

import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.readText

const val LOCAL_DATA_DIR = "data"

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