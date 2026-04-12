package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    companion object {
        private val INPUT = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()
    }

    private val day = Day04()

    @Test
    fun part1() {
        assertEquals(2, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(4, day.part2(INPUT))
    }
}
