package util.core

const val LOCAL_DATA_DIR = "input"

/**
 * AoC solution interface.
 *
 * @param T the return type of the part 1 solution
 * @param U the return type of the part 2 solution
 */
interface Solution<T, U> {
    fun part1(input: String): T
    fun part2(input: String): U
}

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
