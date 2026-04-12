package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    companion object {
        private val INPUT = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()
    }

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(157, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(70, day.part2(INPUT))
    }
}
