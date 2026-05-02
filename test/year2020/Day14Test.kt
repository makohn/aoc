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
    }

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(165, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(0, day.part2(INPUT))
    }
}
