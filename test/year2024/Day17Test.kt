package year2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    companion object {
        private val INPUT1 = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
        """.trimIndent()

        private val INPUT2 = """
            Register A: 2024
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
        """.trimIndent()
    }

    private val day = Day17()

    @Test
    fun part1() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(117440, day.part2(INPUT2))
    }
}
