package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    companion object {
        private val INPUT = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent()
    }

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(21, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(525152, day.part2(INPUT))
    }
}
