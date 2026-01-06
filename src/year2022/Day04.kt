package year2022

import util.core.*

class Day04 : Solution<Int, Int>(year = 2022, day = 4) {

    operator fun IntRange.contains(other: IntRange) = other.first >= this.first && other.last <= this.last
    infix fun IntRange.overlapsWith(other: IntRange) = (
            (other.first >= this.first && other.last <= this.last) ||
            (other.first >= this.first && other.first <= this.last) ||
            (other.last >= this.first && other.last <= this.last))

    override fun part1(input: String): Int {
        var count = 0
        val pairs = mutableListOf<Pair<IntRange, IntRange>>()
        for (l in input.lines()) {
            val p = l.split(",")
            val p1 = p[0].split("-")
            val p2 = p[1].split("-")
            val s1 = p1[0].toInt()
            val e1 = p1[1].toInt()
            val s2 = p2[0].toInt()
            val e2 = p2[1].toInt()
            pairs.add(s1..e1 to s2 .. e2)
        }
        for (pair in pairs) {
            if ((pair.first in pair.second) || (pair.second in pair.first)) {
                count ++
            }
        }
        return count
    }

    override fun part2(input: String): Int {
        var count = 0
        val pairs = mutableListOf<Pair<IntRange, IntRange>>()
        for (l in input.lines()) {
            val p = l.split(",")
            val p1 = p[0].split("-")
            val p2 = p[1].split("-")
            val s1 = p1[0].toInt()
            val e1 = p1[1].toInt()
            val s2 = p2[0].toInt()
            val e2 = p2[1].toInt()
            pairs.add(s1..e1 to s2 .. e2)
        }
        for (pair in pairs) {
            if ((pair.first overlapsWith  pair.second) || (pair.second overlapsWith  pair.first)) {
                count ++
            }
        }
        return count
    }
}

fun main() = Day04().run {
    println(part1(input))
    println(part2(input))
}