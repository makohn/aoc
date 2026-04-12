package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    companion object {
        private val INPUT = """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
        """.trimIndent()
    }

    private val day = Day18()

    @Test
    fun part1() {
        assertEquals(64, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(58, day.part2(INPUT))
    }
}
