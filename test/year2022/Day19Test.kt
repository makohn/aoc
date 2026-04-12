package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {

    companion object {
        private val INPUT = """
            Blueprint 1:
              Each ore robot costs 4 ore.
              Each clay robot costs 2 ore.
              Each obsidian robot costs 3 ore and 14 clay.
              Each geode robot costs 2 ore and 7 obsidian.

            Blueprint 2:
              Each ore robot costs 2 ore.
              Each clay robot costs 3 ore.
              Each obsidian robot costs 3 ore and 8 clay.
              Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent()
    }

    private val day = Day19()

    @Test
    fun part1() {
        assertEquals(33, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(3472, day.part2(INPUT))
    }
}
