package year2020

import util.core.*
import util.point.*

class Day12 : Solution<Int, Int> {

    private data class Command(val command: Char, val amount: Int)

    private fun Point.rotate(amount: Int): Point = when (amount.mod(360)) {
        90 -> this.clockwise()
        180 -> this * -1
        270 -> this.counterClockwise()
        else -> error("Unreachable")
    }

    override fun part1(input: String): Int {
        val commands = input.lines().map { Command(it[0], it.drop(1).toInt()) }
        var position = ORIGIN
        var direction = RIGHT

        for ((command, amount) in commands) {
            when (command) {
                'N' -> position = Point(position.x, position.y - amount)
                'S' -> position = Point(position.x, position.y + amount)
                'E' -> position = Point(position.x + amount, position.y)
                'W' -> position = Point(position.x - amount, position.y)
                'L' -> direction = direction.rotate(-amount)
                'R' -> direction = direction.rotate(amount)
                'F' -> position = position + direction * amount
            }
        }
        return position.distanceTo(ORIGIN)
    }

    override fun part2(input: String): Int {
        val commands = input.lines().map { Command(it[0], it.drop(1).toInt()) }
        var position = ORIGIN
        var waypoint = Point(10, -1)

        for ((command, amount) in commands) {
            when (command) {
                'N' -> waypoint = Point(waypoint.x, waypoint.y - amount)
                'S' -> waypoint = Point(waypoint.x, waypoint.y + amount)
                'E' -> waypoint = Point(waypoint.x + amount, waypoint.y)
                'W' -> waypoint = Point(waypoint.x - amount, waypoint.y)
                'L' -> waypoint = waypoint.rotate(-amount)
                'R' -> waypoint = waypoint.rotate(amount)
                'F' -> position = position + waypoint * amount
            }
        }
        return position.distanceTo(ORIGIN)
    }
}
