package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(3, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(6, day.part2(INPUT))
    }
}
