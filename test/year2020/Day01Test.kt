package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(514579, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(241861950, day.part2(INPUT))
    }
}
