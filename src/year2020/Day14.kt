package year2020

import util.core.*
import util.parse.*

class Day14 : Solution<Long, Long> {

    companion object {
        private const val MEMORY_SIZE = 1 shl 16
    }

    private sealed interface Instruction {
        class Mask(val ones: Long, val xs: Long) : Instruction
        class Mem(val address: Long, val value: Long) : Instruction
    }

    private class Set(
        val ones: Long,
        val floating: Long,
        val weight: Long,
    ) {
        constructor(address: Long, value: Long, ones: Long, floating: Long) : this(
            ones = (address or ones) and floating.inv(),
            floating = floating,
            weight = value,
        )

        fun intersect(other: Set): Set? {
            val disjoint = (this.ones xor other.ones) and (this.floating or other.floating).inv()
            return if (disjoint == 0L) {
                Set(
                    ones = this.ones or other.ones,
                    floating = this.floating and other.floating,
                    weight = 0,
                )
            } else {
                null
            }
        }

        fun size(): Long = 1L shl this.floating.countOneBits()
    }

    private fun subsets(cube: Set, sign: Long, candidates: List<Set>): Long {
        var total = 0L

        for ((i, other) in candidates.withIndex()) {
            val next = cube.intersect(other)
            if (next != null) {
                total += sign * next.size() + subsets(next, -sign, candidates.subList(i + 1, candidates.size))
            }
        }
        return total
    }

    private fun parse(input: String): List<Instruction> = input
        .lines()
        .map { line ->
            if (line.startsWith("mask")) {
                val mask = line.substringAfter(" = ")
                val (ones, xs) = mask.fold(0L to 0L) { (ones, xs), b ->
                    ((ones shl 1) or if (b == '1') 1 else 0) to ((xs shl 1) or if (b == 'X') 1 else 0)
                }
                Instruction.Mask(ones, xs)
            } else {
                val (address, value) = line.extractLongs()
                Instruction.Mem(address, value)
            }
        }

    override fun part1(input: String): Long {
        val program = parse(input)
        val memory = LongArray(MEMORY_SIZE)

        var set = 0L
        var keep = 0L
        for (instruction in program) {
            when (instruction) {
                is Instruction.Mask -> {
                    set = instruction.ones
                    keep = instruction.ones or instruction.xs
                }

                is Instruction.Mem -> {
                    memory[instruction.address.toInt()] = (instruction.value or set) and keep
                }
            }
        }
        return memory.sum()
    }

    override fun part2(input: String): Long {
        val program = parse(input)

        var ones = 0L
        var floating = 0L
        val sets = ArrayList<Set>()

        for (instruction in program) {
            when (instruction) {
                is Instruction.Mask -> {
                    ones = instruction.ones
                    floating = instruction.xs
                }

                is Instruction.Mem -> {
                    sets.add(Set(instruction.address, instruction.value, ones, floating))
                }
            }
        }
        var total = 0L
        val candidates = ArrayList<Set>()

        for ((i, set) in sets.withIndex()) {
            candidates.addAll(sets.subList(i + 1, sets.size).mapNotNull { set.intersect(it) })
            val size = set.size() + subsets(set, -1, candidates)
            total += size * set.weight
            candidates.clear()
        }
        return total
    }
}
