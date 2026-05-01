package year2020

import util.core.*
import util.parse.*

class Day13 : Solution<Int, Long> {

    override fun part1(input: String): Int {
        val (firstLine, secondLine) = input.lines()
        val departureTime = firstLine.toInt()
        val busIds = secondLine.extractInts()
        var elapsedMinutes = 0
        while (true) {
            for (busId in busIds) {
                if ((departureTime + elapsedMinutes) % busId == 0) {
                    return elapsedMinutes * busId
                }
            }
            elapsedMinutes++
        }
    }

    override fun part2(input: String): Long {
        val (_, secondLine) = input.lines()
        val buses = secondLine.split(",")
        val congruences = ArrayList<Pair<Long, Long>>()
        for ((index, bus) in buses.withIndex()) {
            if (bus != "x") {
                val busId = bus.toLong()
                congruences.add((-index).mod(busId) to busId)
            }
        }
        var timestamp = 0L
        var step = 1L
        for ((remainder, modulus) in congruences) {
            while (timestamp % modulus != remainder) {
                timestamp += step
            }
            step *= modulus
        }
        return timestamp
    }
}
