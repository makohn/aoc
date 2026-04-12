package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private val INPUT = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent()
    }

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(50, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(24, day.part2(INPUT))
    }
}
