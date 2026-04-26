package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    companion object {
        private val INPUT = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()
    }

    private val day = Day11()

    @Test
    fun part1() {
        assertEquals(37, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(26, day.part2(INPUT))
    }
}
