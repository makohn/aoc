package year2025

import util.Solution

class Day05 : Solution<Int, Long>(year = 2025, day = 5) {

    override fun part1(input: String): Int {
        val lines = input.lines()
        var fresh = 0
        val sep = lines.indexOfFirst { it.isBlank() }
        val ranges = lines.subList(0, sep).map {
            val (a, b) = it.split("-")
            a.toLong()..b.toLong()
        }
        val ingredients = lines.subList(sep + 1, lines.size).map { it.toLong() }
        nextIng@for (ingredient in ingredients) {
            for (range in ranges) {
                if (ingredient in range) {
                    fresh++
                    continue@nextIng
                }
            }
        }
        return fresh
    }

    override fun part2(input: String): Long {
        val lines = input.lines()
        val sep = lines.indexOfFirst { it.isBlank() }
        val ranges = lines.subList(0, sep).map {
            val (a, b) = it.split("-")
            a.toLong()..b.toLong()
        }
        val dr = ArrayList<LongRange>()
        nextRange@for (range in ranges) {
            var x = range
            val rem = ArrayList<LongRange>()
            for (r in dr) {
                if (x.first >= r.first && x.last <= r.last) {
                    continue@nextRange
                } else if (r.first >= x.first && r.last <= x.last) {
                    rem.add(r)
                } else if (x.first in r.first..r.last) {
                    x = r.last+1..x.last
                } else if (r.first in x.first..x.last) {
                    x = x.first..<r.first
                }
            }
            for (t in rem) {
                dr.remove(t)
            }
            dr.add(x)
        }
        val res = dr.fold(0L) { acc, r -> acc + (1 + r.last - r.first) }
        return res
    }
}

fun main() = Day05().run {
    println(part1(input))
    println(part2(input))
}