package year2024

import util.core.*
import util.grid.*
import util.parse.extractLongs
import util.point.*
import kotlin.math.abs

class Day21 : Solution<Long, Long>(year = 2024, day = 21) {

    private typealias KeyPad = Map<Char, Point>
    private typealias Sequences = HashMap<Pair<Char, Char>, ArrayList<String>>

    private data class Key(val previous: Char, val current: Char, val depth: Int)

    private fun getSequences(): Sequences {
        val (numPad, numGap) = parsePad(
            arrayOf(
                charArrayOf('7', '8', '9'),
                charArrayOf('4', '5', '6'),
                charArrayOf('1', '2', '3'),
                charArrayOf(' ', '0', 'A')
            )
        )

        val (dirPad, dirGap) = parsePad(
            arrayOf(
                charArrayOf(' ', '^', 'A'),
                charArrayOf('<', 'v', '>')
            )
        )

        val sequences: Sequences = HashMap()
        findSequences(sequences, numPad, numGap)
        findSequences(sequences, dirPad, dirGap)

        return sequences
    }

    private fun parsePad(pad: CharGrid): Pair<KeyPad, Point> {
        val map = HashMap<Char, Point>()
        for (i in pad.indices) for (j in pad[0].indices) map[pad[i][j]] = Point(i, j)
        val gap = map.remove(' ')!!
        return map to gap
    }

    private fun findSequences(sequences: Sequences, pad: KeyPad, gap: Point) {
        for ((a, from) in pad) for ((b, to) in pad) {
            val horizontal = createSequence(from.j, to.j, '>', '<')
            val vertical = createSequence(from.i, to.i, 'v', '^')

            if (gap != Point(to.i, from.j)) {
                val sequence = vertical + horizontal + 'A'
                sequences.computeIfAbsent(a to b) { ArrayList() }.add(sequence)
            }

            if (from != to && gap != Point(from.i, to.j)) {
                val sequence = horizontal + vertical + 'A'
                sequences.computeIfAbsent(a to b) { ArrayList() }.add(sequence)
            }
        }
    }

    private fun createSequence(from: Int, to: Int, forward: Char, backward: Char): String {
        val dir = if (from < to) forward else backward
        val n = abs(from - to)
        return dir.toString().repeat(n)
    }

    private fun dfs(cache: HashMap<Key, Long>, sequences: Sequences, code: String, depth: Int): Long {
        if (depth == 0) return code.length.toLong()
        var previous = 'A'
        var result = 0L
        for (current in code) {
            val key = Key(previous, current, depth)
            result += cache.getOrElse(key) {
                sequences[previous to current]!!
                    .minOf { next -> dfs(cache, sequences, next, depth - 1) }
                    .also { cache[key] = it }
            }
            previous = current
        }
        return result
    }

    private fun sumOfComplexities(input: String, depth: Int): Long {
        val codes = input.lines().zip(input.extractLongs())
        val sequences = getSequences()
        val cache = HashMap<Key, Long>(512)
        return codes.sumOf { (code, num) -> dfs(cache, sequences, code, depth) * num }
    }

    override fun part1(input: String): Long {
        return sumOfComplexities(input, 3)
    }

    override fun part2(input: String): Long {
        return sumOfComplexities(input, 26)
    }
}

fun main() = Day21().run {
    println(part1(input))
    println(part2(input))
}