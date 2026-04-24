package year2020

import util.core.*
import util.parse.*

class Day08 : Solution<Int, Int> {

    private sealed interface Instruction {
        class Jmp(val amount: Int) : Instruction
        class Acc(val amount: Int) : Instruction
        object Nop : Instruction
    }

    private fun Instruction(line: String): Instruction {
        val (instruction, amount) = line.splitAsciiWhitespace()
        return when (instruction) {
            "jmp" -> Instruction.Jmp(amount.toInt())
            "acc" -> Instruction.Acc(amount.toInt())
            "nop" -> Instruction.Nop
            else -> error(instruction)
        }
    }

    override fun part1(input: String): Int {
        val program = input.lines().map(::Instruction)
        val executed = BooleanArray(program.size)

        var ip = 0
        var acc = 0
        while (true) {
            if (executed[ip]) break
            executed[ip] = true
            when (val instruction = program[ip]) {
                is Instruction.Acc -> {
                    acc += instruction.amount
                    ip++
                }

                is Instruction.Jmp -> ip += instruction.amount
                is Instruction.Nop -> ip++
            }
        }
        return acc
    }

    override fun part2(input: String): Int = 0
}
