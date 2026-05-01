package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    companion object {
        private val INPUT = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()
    }

    private val day = Day13()

    @Test
    fun part1() {
        assertEquals(295, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1068781, day.part2(INPUT))
    }
}
