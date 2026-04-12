package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    companion object {
        private val INPUT1 = """
            1
            10
            100
            2024
        """.trimIndent()

        private val INPUT2 = """
            1
            2
            3
            2024
        """.trimIndent()
    }

    private val day = Day22()

    @Test
    fun part1() {
        assertEquals(37327623, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(23, day.part2(INPUT2))
    }
}
