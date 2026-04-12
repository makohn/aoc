package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    companion object {
        private val INPUT = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent()
    }

    private val day = Day16()

    @Test
    fun part1() {
        assertEquals(46, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(51, day.part2(INPUT))
    }
}
