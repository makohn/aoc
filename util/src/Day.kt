/**
 * Abstract template for an AoC solution.
 *
 * @param T the return type of the part 1 solution
 * @param U the return type of the part 2 solution
 */
abstract class Day<T, U>(val year: Int, val day: Int) {
    val input = readInput(year, day)
    val testInput by lazy { readTestInput(year, day) }

    abstract fun part1(input: String): T
    abstract fun part2(input: String): U
}