package year2022

import util.core.*

class Day21 : Solution<Long, Long> {

    private enum class Operation {
        Add,
        Sub,
        Mul,
        Div,
        ;

        operator fun invoke(left: Long, right: Long): Long = when (this) {
            Add -> left + right
            Sub -> left - right
            Mul -> left * right
            Div -> left / right
        }
    }

    private fun Operation(op: Char): Operation = when (op) {
        '+' -> Operation.Add
        '-' -> Operation.Sub
        '*' -> Operation.Mul
        '/' -> Operation.Div
        else -> error(op)
    }

    private sealed interface Job {
        data class Number(val value: Long) : Job
        data class MathResult(val left: Long, val operation: Operation, val right: Long) : Job
    }

    private fun Job(str: String, monkeys: Map<String, Long>): Job = if (str.length < 11) {
        Job.Number(str.toLong())
    } else {
        val left = monkeys[str.substring(0, 4)]!!
        val right = monkeys[str.substring(7, 11)]!!
        val operation = Operation(str[5])
        Job.MathResult(left, operation, right)
    }

    private class State(val root: Long, val jobs: List<Job>, size: Int) {
        val results = LongArray(size)
        val dependsOnHuman = BooleanArray(size)
    }

    private fun yell(index: Long, state: State): Long {
        val result = when (val job = state.jobs[index.toInt()]) {
            is Job.Number -> job.value
            is Job.MathResult -> {
                val left = yell(job.left, state)
                val right = yell(job.right, state)
                job.operation(left, right)
            }
        }
        state.results[index.toInt()] = result
        return result
    }

    private fun find(state: State, human: Long, index: Long): Boolean {
        val res = when (val job = state.jobs[index.toInt()]) {
            is Job.Number -> human == index
            is Job.MathResult -> find(state, human, job.left) || find(state, human, job.right)
        }
        state.dependsOnHuman[index.toInt()] = res
        return res
    }

    private fun backSolve(state: State, index: Long, value: Long): Long = when (val job = state.jobs[index.toInt()]) {
        is Job.Number -> value
        is Job.MathResult -> {
            val (left, operation, right) = job
            if (index == state.root) {
                if (state.dependsOnHuman[left.toInt()]) {
                    backSolve(state, left, state.results[right.toInt()])
                } else {
                    backSolve(state, right, state.results[left.toInt()])
                }
            } else {
                if (state.dependsOnHuman[left.toInt()]) {
                    when (operation) {
                        Operation.Add -> backSolve(state, left, value - state.results[right.toInt()])
                        Operation.Sub -> backSolve(state, left, value + state.results[right.toInt()])
                        Operation.Mul -> backSolve(state, left, value / state.results[right.toInt()])
                        Operation.Div -> backSolve(state, left, value * state.results[right.toInt()])
                    }
                } else {
                    when (operation) {
                        Operation.Add -> backSolve(state, right, value - state.results[left.toInt()])
                        Operation.Sub -> backSolve(state, right, state.results[left.toInt()] - value)
                        Operation.Mul -> backSolve(state, right, value / state.results[left.toInt()])
                        Operation.Div -> backSolve(state, right, state.results[left.toInt()] / value)
                    }
                }
            }
        }
    }

    private fun parse(input: String): Pair<State, Map<String, Long>> {
        val lines = input.lines()
        val monkeys = lines.withIndex().associate { (index, line) -> line.substring(0, 4) to index.toLong() }
        val jobs = lines.map { line -> Job(line.substring(6), monkeys) }
        val root = monkeys["root"]!!
        return State(root, jobs, lines.size) to monkeys
    }

    override fun part1(input: String): Long {
        val (state, _) = parse(input)
        yell(state.root, state)
        return state.results[state.root.toInt()]
    }

    override fun part2(input: String): Long {
        val (state, monkeys) = parse(input)
        val human = monkeys["humn"]!!
        yell(state.root, state)
        find(state, human, state.root)
        return backSolve(state, state.root, -1)
    }
}
