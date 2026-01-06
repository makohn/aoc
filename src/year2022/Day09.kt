package year2022

import util.core.*
import kotlin.math.abs
import kotlin.math.sign

class Day09 : Solution<Int, Int>(year = 2022, day = 9) {

    data class Pos(var x: Int, var y: Int)

    infix fun Pos.dist(other: Pos) =
        (this.x - other.x) to (this.y - other.y)

    override fun part1(input: String): Int {
        val head = Pos(0, 0)
        val tail = Pos(0, 0)

        val tailPositions = mutableSetOf(Pos(0, 0))

        for ((op, count) in input.lines().map { it.split(" ") }) {
            repeat(count.toInt()) {
                when (op) {
                    "L" -> head.x--
                    "R" -> head.x++
                    "U" -> head.y++
                    "D" -> head.y--
                }
                val (dx, dy) = head dist tail
                if (abs(dx) > 1 || abs(dy) > 1) {
                    tail.x += dx.sign
                    tail.y += dy.sign
                    tailPositions.add(tail.copy())
                }
            }
        }

        return tailPositions.size
    }

    override fun part2(input: String): Int {
        val knots = Array(10) { Pos(0, 0) }
        val head = knots[0]

        val knotPositions = mutableSetOf(Pos(0,0))

        for ((op, count) in input.lines().map { it.split(" ") }) {
            repeat(count.toInt()) {
                when (op) {
                    "L" -> head.x--
                    "R" -> head.x++
                    "U" -> head.y++
                    "D" -> head.y--
                }
                for (i in 1 until knots.size) {
                    val knot = knots[i]
                    val pred = knots[i-1]
                    val (dx, dy) = pred dist knot
                    if (abs(dx) > 1 || abs(dy) > 1) {
                        knot.x += dx.sign
                        knot.y += dy.sign
                    }
                    if (i == 9) {
                        knotPositions.add(knot.copy())
                    }
                }
            }
        }

        return knotPositions.size
    }
}

fun main() = Day09().run {
    println(part1(input))
    println(part2(input))
}