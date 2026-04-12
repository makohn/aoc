package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    companion object {
        private val INPUT = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
    }

    private val day = Day02()

    @Test
    fun part1() {
        assertEquals(2, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(4, day.part2(INPUT))
    }
}
