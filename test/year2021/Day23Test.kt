package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    companion object {
        private val INPUT = """
            #############
            #...........#
            ###B#C#B#D###
              #A#D#C#A#
              #########
        """.trimIndent()
    }

    private val day = Day23()

    @Test
    fun part1() {
        assertEquals(12521, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(44169, day.part2(INPUT))
    }
}
