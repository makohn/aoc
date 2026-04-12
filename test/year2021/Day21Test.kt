package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    companion object {
        private val INPUT = """
            Player 1 starting position: 4
            Player 2 starting position: 8
        """.trimIndent()
    }

    private val day = Day21()

    @Test
    fun part1() {
        assertEquals(739785, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(444356092776315, day.part2(INPUT))
    }
}
