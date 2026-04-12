package year2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    companion object {
        private val INPUT = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()
    }

    private val day = Day02()

    @Test
    fun part1() {
        assertEquals(2, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(1, day.part2(INPUT))
    }
}