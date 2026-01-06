package year2025

import util.core.*
import util.iter.combineElements
import kotlin.math.min

class Day10 : Solution<Int, Int>(year = 2025, day = 10) {

    override fun part1(input: String): Int {
        var sum = 0
        val r = Regex("\\[(.*)](.*)\\{.*}")
        for (line in input.lines()) {
            val (a, b) = r.matchEntire(line)!!.destructured
            val target = a.withIndex().filter { (_, v) -> v == '#' }.sumOf { 1 shl it.index }
            val buttons = b.trim().split(" ").map {
                it.removeSurrounding("(", ")").split(",").sumOf { n -> 1 shl n.toInt() }
            }

            search@ for (n in 1..buttons.lastIndex) {
                for (combination in buttons.combineElements(n)) {
                    val combined = combination.reduce { x, y -> x xor y }
                    if (combined == target) {
                        sum += n
                        break@search
                    }
                }
            }
        }
        return sum
    }

    override fun part2(input: String): Int {
        val r = Regex("\\[.*](.*)\\{(.*)}")
        var sum = 0
        for (line in input.lines()) {
            val (a, b) = r.matchEntire(line)!!.destructured
            val buttons = a.trim().split(" ").map {
                it.removeSurrounding("(", ")").split(",").map { n -> n.toInt() }.toSet()
            }
            val levels = b.split(",").map { it.toInt() }
            val combinations = (0..buttons.size).flatMap { n ->
                (0..<buttons.size).toList().combineElements(n)
                    .map {
                        it.fold(IntArray(levels.size) { 0 }) { arr, c ->
                            buttons[c].forEach { i -> arr[i] += 1 }
                            arr
                        } to n
                    }
            }.groupBy { List(it.first.size) { i -> it.first[i] % 2 } }.map { (k, v) -> k to v.toMap() }.toMap()

            val cache = HashMap<List<Int>, Int>()
            cache[List(levels.size) { 0 }] = 0

            fun findMin(target: List<Int>): Int {
                if (target in cache) return cache[target]!!
                var curMin = 1_000_000
                val parity = List(target.size) { i -> target[i] % 2 }
                val buttonCombos = combinations[parity] ?: return curMin
                for ((combo, cost) in buttonCombos) {
                    if (combo.zip(target).all { (i, j) -> i <= j }) {
                        val newTarget = target.mapIndexed { i, v -> (v - combo[i]) / 2 }
                        curMin = min(curMin, findMin(newTarget) * 2 + cost)
                    }
                }
                cache[target] = curMin
                return curMin
            }

            sum += findMin(levels)
        }
        return sum
    }
}

fun main() = Day10().run {
    println(part1(input))
    println(part2(input))
}