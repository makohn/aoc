package year2021

import util.Solution

class Day10 : Solution<Int, Long>(year = 2021, day = 10) {

    val open = listOf('(','[','{','<')

    override fun part1(input: String): Int {
        var sum = 0
        for (line in input.lines()) {
            val stack = mutableListOf<Char>()
            for (ch in line) {
                val ret = when(ch) {
                    in open -> stack.add(0, ch).let { ch }
                    '}' -> if (stack.indexOfFirst{ it == '{' } == 0) stack.removeFirst() else ch
                    ')' -> if (stack.indexOfFirst{ it == '(' } == 0) stack.removeFirst() else ch
                    ']' -> if (stack.indexOfFirst{ it == '[' } == 0) stack.removeFirst() else ch
                    '>' -> if (stack.indexOfFirst{ it == '<' } == 0) stack.removeFirst() else ch
                    else -> error("Impossible")
                }
                sum += when(ret) {
                    '}' -> 1197
                    ')' -> 3
                    ']' -> 57
                    '>' -> 25137
                    else -> 0
                }
                if (ret !in open) break
            }
        }
        return sum
    }

    override fun part2(input: String): Long {
        val scores = mutableListOf<Long>()
        score@for (line in input.lines()) {
            var sum = 0L
            val stack = mutableListOf<Char>()
            for (ch in line) {
                val ret = when(ch) {
                    in open -> stack.add(0, ch).let { ch }
                    '}' -> if (stack.indexOfFirst{ it == '{' } == 0) stack.removeFirst() else ch
                    ')' -> if (stack.indexOfFirst{ it == '(' } == 0) stack.removeFirst() else ch
                    ']' -> if (stack.indexOfFirst{ it == '[' } == 0) stack.removeFirst() else ch
                    '>' -> if (stack.indexOfFirst{ it == '<' } == 0) stack.removeFirst() else ch
                    else -> error("Impossible")
                }
                if (ret !in open) continue@score
            }
            sum += stack.fold(0L) { acc, e -> 5 * acc + (open.indexOf(e) + 1) }
            scores += sum
        }
        return scores.sorted()[scores.size/2]
    }
}

fun main() = Day10().run {
    println(part1(input))
    println(part2(input))
}