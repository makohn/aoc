package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private val INPUT = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()
    }

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(114, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(2, day.part2(INPUT))
    }
}
