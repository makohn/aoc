package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    companion object {
        private val INPUT = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """.trimIndent()
    }

    private val day = Day02()

    @Test
    fun part1() {
        assertEquals(150, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(900, day.part2(INPUT))
    }
}
