package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    companion object {
        private const val INPUT = "16,1,2,0,4,2,7,1,2,14"
    }

    private val day = Day07()

    @Test
    fun part1() {
        assertEquals(37, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(168, day.part2(INPUT))
    }
}
