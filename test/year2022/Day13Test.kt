package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    companion object {
        private val INPUT = """
            [1,1,3,1,1]
            [1,1,5,1,1]

            [[1],[2,3,4]]
            [[1],4]

            [9]
            [[8,7,6]]

            [[4,4],4,4]
            [[4,4],4,4,4]

            [7,7,7,7]
            [7,7,7]

            []
            [3]

            [[[]]]
            [[]]

            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
        """.trimIndent()
    }

    private val day = Day13()

    @Test
    fun part1() {
        assertEquals(13, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(140, day.part2(INPUT))
    }
}
