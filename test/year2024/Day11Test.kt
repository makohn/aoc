package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object {
        private const val INPUT = "125 17"
    }

    private val day = Day11()

    @Test
    fun part1() {
        assertEquals(55312, day.part1(INPUT))
    }
}
