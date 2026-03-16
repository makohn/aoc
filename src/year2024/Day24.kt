package year2024

import util.core.*
import util.parse.*

class Day24 : Solution<Long, String>(year = 2024, day = 24) {

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

    override fun part2(input: String): String {
        val (_, gateStr) = input.split("\n\n")
        val gates = gateStr.splitAsciiWhitespace().chunked(5)
        val output = HashSet<Pair<String, String>>()
        val res = HashSet<String>()

        for ((left, type, right) in gates) {
            output.add(left to type)
            output.add(right to type)
        }

        for ((left, type, right, _, to) in gates) when (type) {
            "AND" -> if (left != "x00" && right != "x00" && (to to "OR") !in output) {
                res.add(to)
            }
            "OR" -> if ((to.startsWith('z') && to != "z45") || (to to "OR") in output) {
                res.add(to)
            }
            "XOR" -> {
                if (left.startsWith('x') || right.startsWith('x')) {
                    if (left != "x00" && right != "x00" && (to to "XOR") !in output) {
                        res.add(to)
                    }
                } else if (!to.startsWith('z')) res.add(to)
            }
            else -> error("Unreachable")
        }
        return res.sorted().joinToString(",")
    }
}