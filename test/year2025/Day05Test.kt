package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    companion object {
        private val INPUT = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent()
    }

    private val day = Day05()

    @Test
    fun part1() {
        assertEquals(3, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(14, day.part2(INPUT))
    }
}
