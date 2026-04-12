package year2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    companion object {
        private val INPUT = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
        """.trimIndent()
    }

    private val day = Day21()

    @Test
    fun part1() {
        assertEquals(152, day.part1(INPUT))
    }

    @Test
    fun part2() {
        assertEquals(301, day.part2(INPUT))
    }
}
