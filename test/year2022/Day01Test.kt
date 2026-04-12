package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    companion object {
        private val INPUT = """
            1000
            2000
            3000
            
            4000
            
            5000
            6000
            
            7000
            8000
            9000
            
            10000
        """.trimIndent()
    }

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(24000, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(45000, day.part2(INPUT))
    }
}
