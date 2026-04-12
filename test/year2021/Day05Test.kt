package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    companion object {
        private val INPUT = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
    }

    private val day = Day05()

    @Test
    fun part1() {
        assertEquals(5, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(12, day.part2(INPUT))
    }
}
