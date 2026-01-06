package year2025

import util.Solution

class Day11 : Solution<Int, Long>(year = 2025, day = 11) {

    override fun part1(input: String): Int {
        val connections = input.lines().map { it.split(":") }.associate { (m, t) -> m to t.trim().split(" ") }
        val start = connections["you"]!!

        var count = 0
        val q = ArrayList<List<String>>()
        q.add(start)
        while (q.isNotEmpty()) {
            val targets = q.removeFirst()
            for (target in targets) {
                if (target == "out") count++
                else q.add(connections[target]!!)
            }
        }

        return count
    }

    override fun part2(input: String): Long {
        val connections = input.lines().map { it.split(":") }.associate { (m, t) -> m to t.trim().split(" ") }
        data class Node(val value: String, val dac: Boolean, val fft: Boolean)

        val cache = HashMap<Node, Long>()
        fun findPath(node: Node): Long {
            if (node in cache) return cache[node]!!
            if (node.value == "out") return if (node.dac && node.fft) 1 else 0
            var count = 0L
            val targets = connections[node.value]!!
            for (target in targets) {
                count += findPath(Node(target, if (target == "dac") true else node.dac, if (target == "fft") true else node.fft))
            }
            cache[node] = count
            return count
        }

        return findPath(Node("svr", dac = false, fft = false))
    }
}

fun main() = Day11().run {
    println(part1(input))
    println(part2(input))
}