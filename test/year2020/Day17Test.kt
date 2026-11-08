package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    companion object {
        private val INPUT = """
            .#.
            ..#
            ###
        """.trimIndent()
    }

    private val day = Day17()

    @Test
    fun part1() {
        assertEquals(112, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(848, day.part2(INPUT))
    }
}
