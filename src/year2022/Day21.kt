package year2022

import util.core.*

class Day21 : Solution<Long, Int>(year = 2022, day = 21) {

    private enum class Operation {
        Add, Sub, Mul, Div;

        operator fun invoke(left: Long, right: Long): Long = when (this) {
            Add -> left + right
            Sub -> left - right
            Mul -> left * right
            Div -> left / right
        }
    }

    private fun Operation(op: Char) = when (op) {
        '+' -> Operation.Add
        '-' -> Operation.Sub
        '*' -> Operation.Mul
        '/' -> Operation.Div
        else -> error(op)
    }

    private sealed interface Job {
        data class Number(val value: Long): Job
        data class MathResult(val left: Long, val operation: Operation, val right: Long): Job
    }

    private fun Job(str: String, monkeys: Map<String, Long>): Job {
        return if (str.length < 11) Job.Number(str.toLong()) else {
            val left = monkeys[str.substring(0, 4)]!!
            val right = monkeys[str.substring(7, 11)]!!
            val operation = Operation(str[5])
            Job.MathResult(left, operation, right)
        }
    }

    private class State(val jobs: List<Job>, size: Int) {
        val yelledNumbers = LongArray(size)
    }

    private fun yell(monkeyIndex: Long, state: State): Long {
        val result = when (val job = state.jobs[monkeyIndex.toInt()]) {
            is Job.Number -> job.value
            is Job.MathResult -> {
                val left = yell(job.left, state)
                val right = yell( job.right, state)
                job.operation(left, right)
            }
        }
        state.yelledNumbers[monkeyIndex.toInt()] = result
        return result
    }

    override fun part1(input: String): Long {
        val lines = input.lines()
        val monkeys = lines.withIndex().associate { (index, line) -> line.substring(0, 4) to index.toLong() }
        val jobs = lines.map { line -> Job(line.substring(6), monkeys) }
        val root = monkeys["root"]!!
        val state = State(jobs, lines.size)
        yell(root, state)
        return state.yelledNumbers[root.toInt()]
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() {
    Day21().run {
        println(part1(input))
    }
}