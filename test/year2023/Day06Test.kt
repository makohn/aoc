package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        private val INPUT = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
    }

    private val day = Day06()

    @Test
    fun part1() {
        assertEquals(288, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(71503, day.part2(INPUT))
    }
}
