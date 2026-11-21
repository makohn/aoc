package year2020

import util.core.*

class Day18 : Solution<Long, Long> {

    private fun nextCharIn(expression: CharIterator): Char? = if (!expression.hasNext()) {
        null
    } else {
        when (val next = expression.next()) {
            ')' -> null
            ' ' -> expression.next()
            else -> next
        }
    }

    private fun valueOfNextCharIn(expression: CharIterator, evaluate: (CharIterator) -> Long): Long = when (val char = nextCharIn(expression)!!) {
        '(' -> evaluate(expression)
        else -> char.digitToInt().toLong()
    }

    override fun part1(input: String): Long {
        fun evaluate(expression: CharIterator): Long {
            var total = valueOfNextCharIn(expression, ::evaluate)
            while (true) {
                val operation = nextCharIn(expression) ?: break
                val value = valueOfNextCharIn(expression, ::evaluate)
                when (operation) {
                    '+' -> total += value
                    else -> total *= value
                }
            }
            return total
        }
        return input.lines().sumOf { evaluate(it.iterator()) }
    }

    override fun part2(input: String): Long {
        fun evaluate(expression: CharIterator): Long {
            var total = valueOfNextCharIn(expression, ::evaluate)
            while (true) {
                val operation = nextCharIn(expression) ?: break
                when (operation) {
                    '+' -> total += valueOfNextCharIn(expression, ::evaluate)
                    else -> return total * evaluate(expression)
                }
            }
            return total
        }
        return input.lines().sumOf { evaluate(it.iterator()) }
    }
}
