package year2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object {
        private val INPUT = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent()
    }

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(226, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(3509, day.part2(INPUT))
    }
}
