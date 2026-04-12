package year2025

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    companion object {
        private val INPUT = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
        """.trimIndent()
    }

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(7, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(33, day.part2(INPUT))
    }
}
