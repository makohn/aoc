package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    companion object {
        private const val INPUT = "1 + (2 * 3) + (4 * (5 + 6))"
        private const val INPUT1 = "2 * 3 + (4 * 5)"
        private const val INPUT2 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        private const val INPUT3 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        private const val INPUT4 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
    }

    private val day = Day18()

    @Test
    fun part1() {
        assertEquals(51, day.part1(INPUT))
        assertEquals(26, day.part1(INPUT1))
        assertEquals(437, day.part1(INPUT2))
        assertEquals(12240, day.part1(INPUT3))
        assertEquals(13632, day.part1(INPUT4))
    }

    @Test
    fun part2() {
        assertEquals(51, day.part2(INPUT))
        assertEquals(46, day.part2(INPUT1))
        assertEquals(1445, day.part2(INPUT2))
        assertEquals(669060, day.part2(INPUT3))
        assertEquals(23340, day.part2(INPUT4))
    }
}
