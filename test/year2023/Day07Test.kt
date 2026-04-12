package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    companion object {
        private val INPUT = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
    }

    private val day = Day07()

    @Test
    fun part1() {
        assertEquals(6440, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(5905, day.part2(INPUT))
    }
}
