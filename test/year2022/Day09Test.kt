package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    companion object {
        private val INPUT1 = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent()

        private val INPUT2 = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent()
    }

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(13, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(36, day.part2(INPUT2))
    }
}
