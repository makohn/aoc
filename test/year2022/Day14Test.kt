package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    companion object {
        private val INPUT = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent()
    }

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(24, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(93, day.part2(INPUT))
    }
}
