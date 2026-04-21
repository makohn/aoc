package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    companion object {
        private val INPUT = """
            FBFBBFFLRR
            FBFBBFFRLR
        """.trimIndent()
    }

    private val day = Day05()

    @Test
    fun part1() {
        assertEquals(357, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(356, day.part2(INPUT))
    }
}
