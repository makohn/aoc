package year2023

import util.core.*
import kotlin.math.max
import kotlin.math.min

class Day05 : Solution<Long, Long>(year = 2023, day = 5) {

    fun LongRange.mapBy(sourceRange: LongRange, destRange: LongRange): Pair<LongRange, List<LongRange>> {
        // `this intersect sourceRange` is slow
        val intersect = max(this.first, sourceRange.first) .. min(this.last, sourceRange.last)
        if (intersect.isEmpty()) {
            return LongRange.EMPTY to listOf(this)
        }
        val diff = destRange.first - sourceRange.first
        val mapped = intersect.first() + diff .. intersect.last() + diff
        val start = this.first ..< intersect.first()
        val end = intersect.last() + 1 .. this.last
        return mapped to listOf(start, end).filter { !it.isEmpty() }
    }

    override fun part1(input: String): Long {
        val inputLines = input.lines()
        val seeds = inputLines
            .first()
            .substringAfter(":")
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toLong() }

        val maps = mutableMapOf<String, List<Pair<LongRange, LongRange>>>()
        var listOfMap = mutableListOf<Pair<LongRange, LongRange>>()
        for (line in inputLines.drop(1).filter { it.isNotEmpty() }) {
            if (line.contains("map")) {
                listOfMap = ArrayList()
                maps[line] = listOfMap
            } else {
                val (dest, source, len) = line.split(" ").map { it.toLong() }
                val destRange = dest..dest+len
                val sourceRange = source..source+len
                listOfMap.add(sourceRange to destRange)
            }
        }

        val numbers = HashMap<Long, Long>()
        for (seed in seeds) {
            var n = seed
            for ((_, v) in maps) {
                for (mapping in v) {
                    if (n in mapping.first) {
                        // second: destRange, first: sourceRange
                        n += mapping.second.first - mapping.first.first
                        break
                    }
                }
                numbers[seed] = n
            }
        }

        return numbers.values.min()
    }

    override fun part2(input: String): Long {
        val inputLines = input.lines()
        val seedRanges = inputLines
            .first()
            .substringAfter(":")
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .chunked(2)
            .map { (a, b) -> a..<a+b }

        val maps = mutableMapOf<String, List<Pair<LongRange, LongRange>>>()
        var listOfMap = mutableListOf<Pair<LongRange, LongRange>>()
        for (line in inputLines.drop(1).filter { it.isNotEmpty() }) {
            if (line.contains("map")) {
                listOfMap = ArrayList()
                maps[line] = listOfMap
            } else {
                val (dest, source, len) = line.split(" ").map { it.toLong() }
                val destRange = dest..dest+len
                val sourceRange = source..source+len
                listOfMap.add(sourceRange to destRange)
            }
        }

        var ranges = seedRanges.toMutableList()

        for ((_, map) in maps) {
            val remainingRanges = ArrayDeque<LongRange>()
            remainingRanges.addAll(ranges)
            val mappedRanges = mutableListOf<LongRange>()
            for (mapping in map) {
                val rem = mutableListOf<LongRange>()
                while (remainingRanges.isNotEmpty()) {
                    val (mapped, remaining) = remainingRanges.removeFirst().mapBy(mapping.first, mapping.second)
                    if (!mapped.isEmpty()) mappedRanges.add(mapped)
                    rem.addAll(remaining)
                }
                remainingRanges.addAll(rem)
            }
            mappedRanges.addAll(remainingRanges)
            ranges = mappedRanges
        }

        return ranges.map { it.first }.min()
    }
}

fun main() = Day05().run {
    println(part1(input))
    println(part2(input))
}