@file:JvmName("Main")

import util.core.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main(args: Array<String>) {
    val input = Path("$LOCAL_DATA_DIR/${args[0].replace('.', '/')}.txt").readText().trimEnd()
    val solution = Class.forName(args[0]).getDeclaredConstructor().newInstance() as Solution<*, *>
    println(solution.part1(input))
    println(solution.part2(input))
}
