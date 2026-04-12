package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

    companion object {
        private val INPUT = """
            #.######
            #>>.<^<#
            #.<..<<#
            #>v.><>#
            #<^v^^>#
            ######.#
        """.trimIndent()
    }

    private val day = Day24()

    @Test
    fun part1() {
        assertEquals(18, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(54, day.part2(INPUT))
    }
}
