package year2023

import util.core.*
import util.math.lcm

class Day08 : Solution<Int, Long>(year = 2023, day = 8) {

    override fun part1(input: String): Int {
        val inputLines = input.lines()
        val instructions = inputLines.first()
        val paths = inputLines
            .drop(2)
            .map { Regex("[A-Z][A-Z][A-Z]").findAll(it).map { it.value } }
            .associate { seq ->
                val (a, b, c) = seq.toList()
                a to (b to c)
            }

        var steps = 0
        var current = "AAA"

        while (true) {
            val instruction = instructions[steps%instructions.length]
            if (current == "ZZZ") break
            steps++
            val path = paths[current]!!
            current = if (instruction == 'L') path.first else path.second
        }
        return steps
    }

    override fun part2(input: String): Long {
        val inputLines = input.lines()
        val instructions = inputLines.first()
        val paths = inputLines
            .drop(2)
            .map { Regex("[A-Z0-9][A-Z0-9][A-Z0-9]").findAll(it).map { it.value } }
            .associate { seq ->
                val (a, b, c) = seq.toList()
                a to (b to c)
            }

        var steps = 0L
        val stepsPerPath = mutableMapOf<Int, Long>()
        var currentNodes = paths.keys.filter { it.endsWith('A') }

        while (true) {
            val nextNodes = mutableListOf<String>()
            for ((i, current) in currentNodes.withIndex()) {
                val pos = (steps.toInt())%instructions.length
                val instruction = instructions[pos]
                val path = paths[current]!!
                val nextNode = if (instruction == 'L') path.first else path.second
                if (nextNode.endsWith('Z')) {
                    stepsPerPath[i] = steps + 1
                    if (stepsPerPath.size == currentNodes.size) {
                        return stepsPerPath.values.lcm()
                    }
                }
                nextNodes.add(nextNode)
            }
            currentNodes = nextNodes
            steps++
        }
    }
}

fun main() = Day08().run {
    println(part1(input))
    println(part2(input))
}