package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private val INPUT = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(198, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(230, day.part2(INPUT))
    }
}
