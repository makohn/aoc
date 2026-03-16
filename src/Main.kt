@file:JvmName("Main")

import util.core.*

fun main(args: Array<String>) {
    val solution = Class.forName(args[0]).getDeclaredConstructor().newInstance() as Solution<*, *>
    println(solution.part1(solution.input))
    println(solution.part2(solution.input))
}