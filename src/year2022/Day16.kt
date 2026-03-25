package year2022

import util.core.*
import util.iter.*
import util.math.*
import util.parse.*

class Day16 : Solution<Int, Int>(year = 2022, day = 16) {

    private data class Valve(val name: String, val flow: Int, val tunnels: List<String>) : Comparable<Valve> {
        override fun compareTo(other: Valve): Int {
            return compareValuesBy(this, other, { -it.flow }, { it.name })
        }
    }

    private fun Valve(str: String): Valve {
        val tokens = str.split { !it.isUpperCase() && !it.isDigit() }
        return Valve(
            name = tokens[1],
            flow = tokens[2].toInt(),
            tunnels = tokens.drop(3)
        )
    }

    private data class Input(
        val size: Int,
        val aa: Int,
        val allValves: Int,
        val flow: List<Int>,
        val distance: List<Int>,
        val closest: List<Int>
    )

    private data class State(
        val todo: Int,
        val start: Int,
        val time: Int,
        val pressure: Int
    )

    private fun parse(input: String): Input {
        val valves = input.lines().map(::Valve).sorted()
        val size = valves.count { it.flow > 0 } + 1
        val distance = Array(size * size) { UInt.MAX_VALUE }
        val valvesIndexed = valves.withIndex()
        val indices = valvesIndexed.associate { (i, v) -> v.name to i }

        for ((start, valve) in valvesIndexed.take(size)) {
            distance[start * size + start] = 0u
            for (tunnel in valve.tunnels) {
                var previous = valve.name
                var current = tunnel
                var goal = indices[current]!!
                var total = 1u

                while (goal >= size) {
                    val next = valves[goal].tunnels.find { it != previous }!!
                    previous = current
                    current = next
                    goal = indices[current]!!
                    total += 1u
                }
                distance[start * size + goal] = total
            }
        }
        for (k in 0..<size) for (i in 0..<size) for (j in 0..<size) {
            val candidate = distance[i * size + k].saturatingAdd(distance[k * size + j])
            if (candidate < distance[i * size + j]) distance[i * size + j] = candidate
        }
        for (i in distance.indices) distance[i] += 1u
        val aa = size - 1
        val allValves = (1 shl aa) - 1
        val flow = valves.take(size).map { it.flow }
        val distanceList = distance.map { it.toInt() }
        val closest = distanceList
            .windowed(size, size, false)
            .map { it.filter { d -> d > 1 }.min() }

        return Input(size, aa, allValves, flow, distanceList, closest)
    }

    private fun solve(input: Input, state: State, highScore: (Int, Int) -> Int) {
        val (todo, from, time, pressure) = state
        val score = highScore(todo, pressure)

        for (bit in todo.bits()) {
            val needed = input.distance[from * input.size + bit]
            if (needed >= time) continue

            val todo = todo xor (1 shl bit)
            val time = time - needed
            val pressure = pressure + time * input.flow[bit]
            val heuristic = {
                var valves = todo
                var time = time
                var pressure = pressure

                while (valves > 0 && time > 3) {
                    val to = valves.countTrailingZeroBits()
                    valves = valves xor (1 shl to)
                    time -= input.closest[to]
                    pressure += time * input.flow[to]
                }
                pressure
            }

            if (heuristic() > score) {
                val next = State(todo, bit, time, pressure)
                solve(input, next, highScore)
            }
        }
    }

    override fun part1(input: String): Int {
        val inp = parse(input)
        var score = 0
        val highScore = { _: Int, pressure: Int ->
            score = maxOf(score, pressure)
            score
        }
        val start = State(inp.allValves, inp.aa, 30, 0)
        solve(inp, start, highScore)
        return score
    }

    override fun part2(input: String): Int {
        val inp = parse(input)
        var you = 0
        var remaining = 0
        val highScore1 = { todo: Int, pressure: Int ->
            if (pressure > you) {
                you = pressure
                remaining = todo
            }
            you
        }
        val state1 = State(inp.allValves, inp.aa, 26, 0)
        solve(inp, state1, highScore1)

        var elephant = 0
        val highScore2 = { _: Int, pressure: Int ->
            elephant = maxOf(elephant, pressure)
            elephant
        }
        val state2 = State(remaining, inp.aa, 26, 0)
        solve(inp, state2, highScore2)

        val score = IntArray(inp.allValves + 1)
        val highScore3 = { todo: Int, pressure: Int ->
            val done = inp.allValves xor todo
            score[done] = maxOf(score[done], pressure)
            elephant
        }
        val state3 = State(inp.allValves, inp.aa, 26, 0)
        solve(inp, state3, highScore3)

        var res = you + elephant
        val candidates = score.withIndex().filter { (_, s) -> s > 0 }.sortedBy { it.value }

        for (i in candidates.lastIndex downTo 1) {
            val (mask1, you) = candidates[i]
            if (you * 2 <= res) break

            for (j in i - 1 downTo 0) {
                val (mask2, elephant) = candidates[j]
                if (mask1 and mask2 == 0) {
                    res = maxOf(res, you + elephant)
                    break
                }
            }
        }
        return res
    }
}