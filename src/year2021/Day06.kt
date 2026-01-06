package year2021

import util.Solution

class Day06(
    private val n: Int = 80
) : Solution<Int, Long>(year = 2021, day = 6) {

    override fun part1(input: String): Int {
        var fish = parse(input)
        repeat(n) { fish = iterate(fish) }
        return fish.count()
    }

    override fun part2(input: String): Long {
        val fish = parse(input)
        val counts = MutableList(9) { 0L }
        fish.forEach { counts[it]++ }
        return count(counts, 256)
    }

    private fun parse(input: String): MutableList<Int> {
        return input.lines().first().split(',').map { it.toInt() }.toMutableList()
    }

    private fun iterate(fish: MutableList<Int>): MutableList<Int> {
        var newFish = fish
            .map { it - 1 }
            .toMutableList()
        repeat(newFish.count { it == -1 }) { newFish.add(8) }
        newFish = newFish.map { if (it == -1) 6 else it }.toMutableList()
        return newFish
    }

    private fun count(counts: MutableList<Long>, n: Int): Long {
        repeat(n) {
            val nextGen = counts.removeFirst()
            counts[6] += nextGen
            counts.add(nextGen)
        }
        return counts.fold(0L) { acc, e -> acc + e}
    }
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}