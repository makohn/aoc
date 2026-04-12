package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(11, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(31, day.part2(INPUT))
    }
}
