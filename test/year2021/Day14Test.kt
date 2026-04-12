package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    companion object {
        private val INPUT = """
            NNCB
            
            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent()
    }

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(1588, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(2188189693529, day.part2(INPUT))
    }
}
