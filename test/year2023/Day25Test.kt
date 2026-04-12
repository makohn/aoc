package year2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    companion object {
        private val INPUT = """
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
        """.trimIndent()
    }

    private val day = Day25()

    @Test
    fun part1() {
        assertEquals(54, day.part1(INPUT))
    }
}
