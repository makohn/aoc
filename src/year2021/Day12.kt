package year2021

import util.core.*

class Day12 : Solution<Int, Int>(year = 2021, day = 12) {

    override fun part1(input: String): Int {
        val connections = parse(input)
        return findPaths(connections, false)
    }

    override fun part2(input: String): Int {
        val connections = parse(input)
        return findPaths(connections, true)
    }

    private fun parse(input: String): Map<String, List<String>> {
        return input
            .lines()
            .map { it.split("-") }
            .flatMap { listOf(it.first() to it.last(), it.last() to it.first()) }
            .groupBy { it.first }
            .mapValues { it.value.map { v -> v.second }.distinct() }
    }

    private fun findPaths(connections: Map<String, List<String>>, twice: Boolean): Int {

        var paths = 0

        fun nextPaths(cave: String, visited: Set<String>, twice: Boolean) {
            var v = visited
            if (cave == cave.lowercase()) v = visited.union(listOf(cave))
            for (nextCave in connections[cave]!!) {
                if (nextCave == "end") paths++
                else if (nextCave !in v) nextPaths(nextCave, v, twice)
                else if (nextCave != "start" && twice) nextPaths(nextCave, v, false)
            }
        }
        nextPaths("start", mutableSetOf(), twice)
        return paths
    }
}

fun main() = Day12().run {
    println(part1(input))
    println(part2(input))
}