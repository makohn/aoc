package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private val INPUT = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent()
    }

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(15, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1134, day.part2(INPUT))
    }
}
