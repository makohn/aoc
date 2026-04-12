package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(7, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(5, day.part2(INPUT))
    }
}
