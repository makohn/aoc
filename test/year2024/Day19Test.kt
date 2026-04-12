package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {

    companion object {
        private val INPUT = """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
        """.trimIndent()
    }

    private val day = Day19()

    @Test
    fun part1() {
        assertEquals(6, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(16, day.part2(INPUT))
    }
}
