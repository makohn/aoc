package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        private val INPUT = """
            123 328  51 64 
             45 64  387 23 
              6 98  215 314
            *   +   *   +  
        """.trimIndent()
    }

    private val day = Day06()

    @Test
    fun part1() {
        assertEquals(4277556, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(3263827, day.part2(INPUT))
    }
}
