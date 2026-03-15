package year2024

import util.core.*
import util.parse.splitAsciiWhitespace

class Day24 : Solution<Long, Int>(year = 2024, day = 24) {

    private fun String.index() =
        ((this[0].code and 31) shl 10) + ((this[1].code and 31) shl 5) + (this[2].code and 31)

    override fun part1(input: String): Long {
        val (wires, gateStr) = input.split("\n\n")
        val gates = gateStr.splitAsciiWhitespace().chunked(5)
        val todo = ArrayList<List<String>>(gates)
        val cache = IntArray(1 shl 15) { Int.MAX_VALUE }
        var res = 0L

        for (wire in wires.lines()) {
            val name = wire.substring(0, 3)
            val value = wire.substring(5).toInt()
            cache[name.index()] = value
        }

        while (todo.isNotEmpty()) {
            val gate = todo.removeFirst()
            val (l, type, r, _, to) = gate
            val left = cache[l.index()]
            val right = cache[r.index()]
            if (left == Int.MAX_VALUE || right == Int.MAX_VALUE) {
                todo.add(gate)
            } else {
                cache[to.index()] = when (type) {
                    "AND" -> left and right
                    "OR" -> left or right
                    "XOR" -> left xor right
                    else -> error(type)
                }
            }
        }

        for (i in "z46".index() - 1 downTo "z00".index()) {
            if (cache[i] != Int.MAX_VALUE) res = (res shl 1) or cache[i].toLong()
        }
        return res
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day24().run {
    println(part1(input))
    println(part2(input))
}