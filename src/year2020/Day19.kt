package year2020

import util.core.*
import util.parse.*

class Day19 : Solution<Int, Int> {

    private sealed interface Rule {
        data class Letter(val char: Char) : Rule
        data class Follow(val a: Int) : Rule
        data class Choice(val a: Int, val b: Int) : Rule
        data class Sequence(val a: Int, val b: Int) : Rule
        data class Compound(val a: Int, val b: Int, val c: Int, val d: Int) : Rule
    }

    private sealed interface TokenList {
        data object Empty : TokenList
        data class Singleton(val a: Int) : TokenList
        data class Pair(val a: Int, val b: Int) : TokenList
        data class Quadruple(val a: Int, val b: Int, val c: Int, val d: Int) : TokenList
    }

    private fun TokenList(tokens: List<Int>): TokenList = when (tokens.size) {
        0 -> TokenList.Empty
        1 -> TokenList.Singleton(tokens[0])
        2 -> TokenList.Pair(tokens[0], tokens[1])
        4 -> TokenList.Quadruple(tokens[0], tokens[1], tokens[2], tokens[3])
        else -> error("Unreachable")
    }

    private fun parseRules(input: String): Array<Rule> {
        val rules = Array<Rule>(640) { Rule.Letter(' ') }
        for (line in input.lines()) {
            val numbers = line.extractInts()
            val tokens = TokenList(numbers.drop(1))
            rules[numbers[0]] = when (tokens) {
                is TokenList.Empty -> if ('a' in line) {
                    Rule.Letter('a')
                } else {
                    Rule.Letter('b')
                }
                is TokenList.Singleton -> Rule.Follow(tokens.a)
                is TokenList.Pair -> if ('|' in line) {
                    Rule.Choice(tokens.a, tokens.b)
                } else {
                    Rule.Sequence(tokens.a, tokens.b)
                }
                is TokenList.Quadruple -> Rule.Compound(tokens.a, tokens.b, tokens.c, tokens.d)
            }
        }
        return rules
    }

    private fun check(rules: Array<Rule>, rule: Int, message: String, index: Int): Int? {
        fun apply(a: Int) = check(rules, a, message, index)
        fun sequence(a: Int, b: Int) = apply(a)?.let { check(rules, b, message, it) }

        return when (val r = rules[rule]) {
            is Rule.Letter -> if (message.getOrNull(index) == r.char) index + 1 else null
            is Rule.Follow -> apply(r.a)
            is Rule.Choice -> apply(r.a) ?: apply(r.b)
            is Rule.Sequence -> sequence(r.a, r.b)
            is Rule.Compound -> sequence(r.a, r.b) ?: sequence(r.c, r.d)
        }
    }

    override fun part1(input: String): Int {
        val (top, bottom) = input.split("\n\n")
        val rules = parseRules(top)
        val messages = bottom.lines()
        return messages.count { check(rules, 0, it, 0) == it.length }
    }

    override fun part2(input: String): Int {
        val (top, bottom) = input.split("\n\n")
        val rules = parseRules(top)
        val messages = bottom.lines()

        return messages.count { message ->
            fun repeat(rule: Int, start: Int): IndexedValue<Int> = generateSequence(start) { index ->
                check(rules, rule, message, index)
            }.withIndex().last()

            val (first, index) = repeat(42, 0)
            val (second, finalIndex) =
                if (first >= 2) {
                    repeat(31, index)
                } else {
                    IndexedValue(0, index)
                }

            finalIndex == message.length && second >= 1 && second < first
        }
    }
}
