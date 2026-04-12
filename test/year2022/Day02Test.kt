package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    companion object {
        private val INPUT = """
            A Y
            B X
            C Z
        """.trimIndent()
    }

    private val day = Day02()

    @Test
    fun part1() {
        assertEquals(15, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(12, day.part2(INPUT))
    }
}
