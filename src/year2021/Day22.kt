package year2021

import util.core.*
import util.parse.extractLongs

class Day22 : Solution<Long, Long>(year = 2021, day = 22) {

    override fun part1(input: String): Long {
        val steps = parse(input)
        val region = Cuboid(-50, 50, -50, 50, -50, 50)
        val stepsInRegion = steps.mapNotNull { (on, cuboid) ->
            region.intersect(cuboid)?.let { RebootStep(on, it) }
        }
        return solve(stepsInRegion)
    }

    override fun part2(input: String): Long {
        val steps = parse(input)
        return solve(steps)
    }

    private fun solve(steps: List<RebootStep>): Long {
        var sum = 0L
        val candidates = ArrayList<Cuboid>()
        for ((i, step) in steps.withIndex()) {
            if (!step.on) continue
            val cuboid = step.cuboid
            candidates.addAll(
                steps.drop(i + 1).mapNotNull { cuboid.intersect(it.cuboid) }
            )
            sum += cuboid.volume() + subsetSize(cuboid, -1, candidates)
            candidates.clear()
        }
        return sum
    }

    private fun subsetSize(cuboid: Cuboid, sign: Int, candidates: List<Cuboid>): Long {
        var sum = 0L
        for ((i, candidate) in candidates.withIndex()) {
            cuboid.intersect(candidate)?.let {
                sum += sign * it.volume() + subsetSize(it, -sign, candidates.drop(i + 1))
            }
        }
        return sum
    }

    private fun parse(input: String): List<RebootStep> {
        val cuboids = ArrayList<RebootStep>()
        for (line in input.lines()) {
            val (state, ranges) = line.split(' ')
            cuboids.add(RebootStep(state == "on", Cuboid(ranges.extractLongs())))
        }
        return cuboids
    }

    private data class RebootStep(val on: Boolean, val cuboid: Cuboid)

    private data class Cuboid(
        val x1: Long,
        val x2: Long,
        val y1: Long,
        val y2: Long,
        val z1: Long,
        val z2: Long,
    ) {
        fun intersect(other: Cuboid): Cuboid? {
            val x1 = maxOf(this.x1, other.x1)
            val x2 = minOf(this.x2, other.x2)
            val y1 = maxOf(this.y1, other.y1)
            val y2 = minOf(this.y2, other.y2)
            val z1 = maxOf(this.z1, other.z1)
            val z2 = minOf(this.z2, other.z2)
            return if (x1 <= x2 && y1 <= y2 && z1 <= z2) Cuboid(x1, x2, y1, y2, z1, z2) else null
        }

        fun volume(): Long = (x2 - x1 + 1) * (y2 - y1 + 1) * (z2 - z1 + 1)
    }

    private fun Cuboid(longs: List<Long>): Cuboid {
        val (x1, x2, y1, y2, z1, z2) = longs
        return Cuboid(x1, x2, y1, y2, z1, z2)
    }
}

fun main() = Day22().run {
    println(part1(input))
    println(part2(input))
}