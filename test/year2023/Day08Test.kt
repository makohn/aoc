package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    companion object {
        private val INPUT1 = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        private val INPUT2 = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()
    }

    private val day = Day08()

    @Test
    fun part1() {
        assertEquals(2, day.part1(INPUT1))
    }

    @Test
    fun part2() {
        assertEquals(6, day.part2(INPUT2))
    }
}
