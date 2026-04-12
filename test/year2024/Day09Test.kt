package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private const val INPUT = "2333133121414131402"
    }

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(1928, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(2858, day.part2(INPUT))
    }
}
