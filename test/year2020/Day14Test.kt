package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    companion object {
        private val INPUT = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        private val INPUT2 = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()
    }

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(165, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(208, day.part2(INPUT2))
    }
}
