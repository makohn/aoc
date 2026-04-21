package year2024

import util.core.*

class Day23 : Solution<Int, String> {

    companion object {
        private const val ALPHABET_SIZE = 26
        private const val COMBINATIONS = ALPHABET_SIZE * ALPHABET_SIZE
        private const val T_START = ALPHABET_SIZE * 19
        private const val T_END = ALPHABET_SIZE * 20
    }

    private typealias ParseResult = Pair<HashMap<Int, ArrayList<Int>>, Array<BooleanArray>>

    private fun String.index() = ALPHABET_SIZE * (this[0] - 'a') + (this[1] - 'a')
    private fun Int.char() = (this + 'a'.code).toChar()

    private fun parse(input: String): ParseResult {
        val nodes = HashMap<Int, ArrayList<Int>>()
        val edges = Array(COMBINATIONS) { BooleanArray(COMBINATIONS) }

        for (edge in input.lines()) {
            val from = edge.substring(0, 2).index()
            val to = edge.substring(3).index()
            nodes.computeIfAbsent(from) { ArrayList() }.add(to)
            nodes.computeIfAbsent(to) { ArrayList() }.add(from)
            edges[from][to] = true
            edges[to][from] = true
        }

        return nodes to edges
    }

    override fun part1(input: String): Int {
        val (nodes, edges) = parse(input)
        val seen = BooleanArray(COMBINATIONS)
        var res = 0

        for (node1 in T_START..<T_END) {
            val neighbours = nodes[node1]
            neighbours?.let {
                seen[node1] = true
                for (i in neighbours.indices) {
                    val node2 = neighbours[i]
                    for (node3 in neighbours.drop(i)) {
                        if (!seen[node2] && !seen[node3] && edges[node2][node3]) res++
                    }
                }
            }
        }
        return res
    }

    override fun part2(input: String): String {
        val (nodes, edges) = parse(input)
        val seen = BooleanArray(COMBINATIONS)
        val set = ArrayList<Int>()
        val largestSet = ArrayList<Int>()

        for ((node1, neighbours) in nodes) {
            if (!seen[node1]) {
                set.clear()
                set.add(node1)

                for (node2 in neighbours) {
                    if (set.all { edges[node2][it] }) {
                        seen[node2] = true
                        set.add(node2)
                    }
                }

                if (set.size > largestSet.size) {
                    largestSet.clear()
                    largestSet.addAll(set)
                }
            }
        }
        val res = StringBuilder()
        largestSet.sort()
        for (node in largestSet) {
            res
                .append((node / ALPHABET_SIZE).char())
                .append((node % ALPHABET_SIZE).char())
                .append(',')
        }
        return res.dropLast(1).toString()
    }
}
