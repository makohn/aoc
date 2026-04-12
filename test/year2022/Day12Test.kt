package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object {
        private val INPUT = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent()
    }

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(31, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(29, day.part2(INPUT))
    }
}
