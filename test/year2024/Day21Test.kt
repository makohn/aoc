package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    companion object {
        private val INPUT = """
            029A
            980A
            179A
            456A
            379A
        """.trimIndent()
    }

    private val day = Day21()

    @Test
    fun part1() {
        assertEquals(126384, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(154115708116294, day.part2(INPUT))
    }
}
