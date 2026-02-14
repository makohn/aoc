package year2024

import util.core.*
import util.parse.extractInts

class Day17 : Solution<String, Long>(year = 2024, day = 17) {

    private class Computer(
        private val program: List<Int>,
        private var a: Long,
        private var b: Long = 0,
        private var c: Long = 0
    ) {
        private var ip: Int = 0

        private val combo
            get() = when (val n = program[ip + 1]) {
                in 0..3 -> n.toLong()
                4 -> a
                5 -> b
                6 -> c
                else -> error(n)
            }
        private val literal get() = program[ip + 1]

        fun eval() = sequence {
            while (ip < program.size) {
                when (program[ip]) {
                    0 -> a = a shr combo.toInt()
                    1 -> b = b xor literal.toLong()
                    2 -> b = combo % 8
                    3 -> if (a != 0L) {
                        ip = literal
                        continue
                    }
                    4 -> b = b xor c
                    5 -> yield(combo % 8)
                    6 -> b = a shr combo.toInt()
                    7 -> c = a shr combo.toInt()
                }
                ip += 2
            }
        }
    }

    private fun findMinA(program: List<Int>, index: Int, a: Long): Long? {
        if (index == -1) return a

        for (i in 0..<8) {
            val nextA = (a shl 3) + i
            val out = Computer(program, nextA).eval().first()

            if (out == program[index].toLong()) {
                return findMinA(program, index - 1, nextA) ?: continue
            }
        }
        return null
    }

    override fun part1(input: String): String {
        val ints = input.extractInts()
        val program = ints.drop(3)
        val computer = Computer(program, ints[0].toLong())
        return computer.eval().joinToString(",")
    }

    override fun part2(input: String): Long {
        val ints = input.extractInts()
        val program = ints.drop(3)
        return findMinA(program, program.lastIndex, 0)!!
    }
}

fun main() = Day17().run {
    println(part1(input))
    println(part2(input))
}