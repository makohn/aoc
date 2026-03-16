package year2024

import util.core.*

class Day19 : Solution<Int, Long>(year = 2024, day = 19) {

    class Node {
        val next = IntArray(6)

        fun setTowel() {
            next[3] = 1
        }

        fun towels() = next[3]
    }

    private fun Char.perfectHash(): Int {
        val n = this.code
        return (n xor (n shr 4)) and 7
    }

    private inline fun solve(input: String, fn: (Long) -> Unit) {
        val (a, b) = input.split("\n\n")
        val towels = a.split(", ")
        val designs = b.lines()

        val trie = ArrayList<Node>(1000)
        trie.add(Node())

        for (towel in towels) {
            var i = 0
            for (j in towel.map { it.perfectHash() }) {
                if (trie[i].next[j] == 0) {
                    trie[i].next[j] = trie.size
                    i = trie.size
                    trie.add(Node())
                } else {
                    i = trie[i].next[j]
                }
            }
            trie[i].setTowel()
        }

        for (design in designs) {
            val size = design.length

            val ways = LongArray(size + 1)
            ways[0] = 1

            for (start in 0..<size) {
                if (ways[start] > 0) {
                    var i = 0
                    for (end in start..<size) {
                        i = trie[i].next[design[end].perfectHash()]
                        if (i == 0) break
                        ways[end + 1] += trie[i].towels() * ways[start]
                    }
                }
            }

            val total = ways[size]
            fn(total)
        }
    }

    override fun part1(input: String): Int {
        var sum = 0
        solve(input) { total -> sum += if (total > 0) 1 else 0 }
        return sum
    }

    override fun part2(input: String): Long {
        var sum = 0L
        solve(input) { total -> sum += total }
        return sum
    }
}