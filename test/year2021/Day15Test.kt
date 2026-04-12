package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    companion object {
        private val INPUT = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
    }

    private val day = Day15()

    @Test
    fun part1() {
        assertEquals(40, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(315, day.part2(INPUT))
    }
}
