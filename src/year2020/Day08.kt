package year2020

import util.core.*
import util.parse.*

class Day08 : Solution<Int, Int> {

    private sealed interface Instruction {
        @JvmInline
        value class Jmp(val amount: Int) : Instruction

        @JvmInline
        value class Acc(val amount: Int) : Instruction

        @JvmInline
        value class Nop(val amount: Int) : Instruction
    }

    private fun Instruction(line: String): Instruction {
        val (instruction, amount) = line.splitAsciiWhitespace()
        return when (instruction) {
            "jmp" -> Instruction.Jmp(amount.toInt())
            "acc" -> Instruction.Acc(amount.toInt())
            "nop" -> Instruction.Nop(amount.toInt())
            else -> error(instruction)
        }
    }

    private class Handheld(private val program: List<Instruction>) {

        sealed interface State {
            val acc: Int

            @JvmInline
            value class Terminated(override val acc: Int) : State

            @JvmInline
            value class Loop(override val acc: Int) : State
        }

        private val executed = BooleanArray(program.size)

        fun boot(ip: Int = 0, acc: Int = 0): State {
            var ip = ip
            var acc = acc
            while (true) {
                if (ip >= program.size) {
                    return State.Terminated(acc)
                } else if (executed[ip]) {
                    return State.Loop(acc)
                }
                executed[ip] = true
                when (val instruction = program[ip]) {
                    is Instruction.Acc -> {
                        acc += instruction.amount
                        ip++
                    }

                    is Instruction.Jmp -> {
                        ip += instruction.amount
                    }

                    is Instruction.Nop -> {
                        ip++
                    }
                }
            }
        }

        fun selfCorrectingBoot(): Int {
            var ip = 0
            var acc = 0
            while (true) {
                when (val instruction = program[ip]) {
                    is Instruction.Acc -> {
                        acc += instruction.amount
                        ip++
                    }

                    is Instruction.Jmp -> {
                        val correctedIp = ip + 1
                        when (val state = boot(correctedIp, acc)) {
                            is State.Loop -> ip += instruction.amount
                            is State.Terminated -> return state.acc
                        }
                    }

                    is Instruction.Nop -> {
                        val correctedIp = ip + instruction.amount
                        when (val state = boot(correctedIp, acc)) {
                            is State.Loop -> ip++
                            is State.Terminated -> return state.acc
                        }
                    }
                }
            }
        }
    }

    override fun part1(input: String): Int {
        val program = input.lines().map(::Instruction)
        return Handheld(program).boot().acc
    }

    override fun part2(input: String): Int {
        val program = input.lines().map(::Instruction)
        return Handheld(program).selfCorrectingBoot()
    }
}
