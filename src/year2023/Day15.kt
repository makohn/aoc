package year2023

import util.Solution
import util.iter.replaceFirst
import kotlin.text.iterator

class Day15 : Solution<Int, Int>(year = 2023, day = 15) {

    fun hash(str: String): Int {
        var curVal = 0
        for (c in str) {
            curVal += c.code
            curVal *= 17
            curVal %= 256
        }
        return curVal
    }

    override fun part1(input: String): Int {
        val sequence = input.lines().first().split(",")
        val ans = sequence.sumOf { hash(it) }
        return ans
    }

    data class Lens(val label: String, val focalLength: Int) {
        override fun toString(): String {
            return "[$label $focalLength]"
        }
    }

    override fun part2(input: String): Int {
        val sequence = input.lines().first().split(",")
        val boxes = Array(256) { mutableListOf<Lens>() }
        for (str in sequence) {
            val (label, opChar, focal) = Regex("([a-z]+)([-=])(\\d?)")
                .findAll(str)
                .flatMap { it.groupValues }
                .drop(1)
                .toList()
            val hash = hash(label)
            val focalLen = if (focal != "") focal.toInt() else 0
            when (opChar) {
                "-" -> boxes[hash].removeIf { it.label == label }
                "=" -> {
                    if (boxes[hash].any { it.label == label }) {
                        boxes[hash].replaceFirst(Lens(label, focalLen)) { it.label == label }
                    } else {
                        boxes[hash].add(Lens(label, focalLen))
                    }
                }
            }
        }
        var ans = 0
        for ((i, box) in boxes.withIndex()) {
            for ((j, lens) in box.withIndex()) {
                ans += (i+1)*(j+1)*lens.focalLength
            }
        }
        return ans
    }
}

fun main() = Day15().run {
    println(part1(input))
    println(part2(input))
}