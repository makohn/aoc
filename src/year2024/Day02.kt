package year2024

import util.Solution
import kotlin.math.abs

class Day02 : Solution<Int, Int>(year = 2024, day = 2) {

    override fun part1(input: String): Int {
        var acc = 0
        for (line in input.lines()) {
            val nums = line.split(" ").map { it.toInt() }
            val decreasing = nums[0] > nums[1]
            val res = nums
                .zipWithNext()
                .map { (a, b) -> (if (decreasing) a > b else a < b) && abs(a - b) in 1..3}
                .all { it }
            if (res) acc++
        }
        return acc
    }

    private fun check(nums: List<Int>): Boolean {
        val decreasing = nums.sortedDescending() == nums
        return nums
            .zipWithNext()
            .map { (a, b) -> (if (decreasing) a > b else a < b) && abs(a - b) in 1..3 }
            .all { it }
    }

    override fun part2(input: String): Int {
        var acc = 0
        for (line in input.lines()) {
            val nums = line.split(" ").map { it.toInt() }
            val res = check(nums) || nums.mapIndexed { i, _ ->
                check(nums.subList(0, i) + nums.subList(i+1, nums.size))
            }.any { it }
            if (res) acc++
        }
        return acc
    }
}

fun main() = Day02().run {
    println(part1(input))
    println(part2(input))
}