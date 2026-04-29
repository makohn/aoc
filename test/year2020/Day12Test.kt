package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object {
        private val INPUT = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()
    }

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(25, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(286, day.part2(INPUT))
    }
}
