package year2024

import util.core.*

class Day25 : Solution<Int, Int>(year = 2024, day = 25) {

    override fun part1(input: String): Int {
        val locks = ArrayList<Int>(256)
        val keys = ArrayList<Int>(256)
        var res = 0

        for (segment in input.chunked(43)) {
            val bits = segment.substring(6, 35).fold(0) { b, c -> (b shl 1) or (c.code and 1) }
            if (segment[0] == '#') locks.add(bits) else keys.add(bits)
        }

        for (lock in locks) for (key in keys) if (lock and key == 0) res++
        return res
    }

    override fun part2(input: String): Int = 0
}
