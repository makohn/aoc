package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    companion object {
        private val INPUT = """
            1
            2
            -3
            3
            -2
            0
            4
        """.trimIndent()
    }

    private val day = Day20()

    @Test
    fun part1() {
        assertEquals(3, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1623178306, day.part2(INPUT))
    }
}
