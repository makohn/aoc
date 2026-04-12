package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    companion object {
        private val INPUT = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """.trimIndent()
    }

    private val day = Day18(7, 12)

    @Test
    fun part1() {
        assertEquals(22, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals("6,1", day.part2(INPUT))
    }
}
