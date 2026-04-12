package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    companion object {
        private val INPUT = """
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>
        """.trimIndent()
    }

    private val day = Day25()

    @Test
    fun part1() {
        assertEquals(58, day.part1(INPUT))
    }
}
