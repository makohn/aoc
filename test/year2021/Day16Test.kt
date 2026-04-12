package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    companion object {
        private const val INPUT1 = "8A004A801A8002F478"
        private const val INPUT2 = "C200B40A82"
    }

    private val day = Day16()

    @Test
    fun part1() {
        assertEquals(16, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(3, day.part2(INPUT2))
    }
}
