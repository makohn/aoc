import kotlin.math.abs

fun main() {

    val day = "02"

    fun part1(input: List<String>): Int {
        var acc = 0
        for (line in input) {
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

    fun check(nums: List<Int>): Boolean {
        val decreasing = nums.sortedDescending() == nums
        return nums
            .zipWithNext()
            .map { (a, b) -> (if (decreasing) a > b else a < b) && abs(a - b) in 1..3 }
            .all { it }
    }

    fun part2(input: List<String>): Int {
        var acc = 0
        for (line in input) {
            val nums = line.split(" ").map { it.toInt() }
            val res = check(nums) || nums.mapIndexed { i, _ ->
                check(nums.subList(0, i) + nums.subList(i+1, nums.size))
            }.any { it }
            if (res) acc++
        }
        return acc
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day${day}")
    println(part1(input))
    println(part2(input))
}