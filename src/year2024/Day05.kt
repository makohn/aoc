package year2024

import util.core.*

class Day05 : Solution<Int, Int>(year = 2024, day = 5) {

    private fun List<Int>.hasElementsInThisOrder(pair: Pair<Int, Int>): Boolean {
        val i = indexOf(pair.first)
        val j = indexOf(pair.second)
        return i <= j || i == -1 || j == -1
    }

    override fun part1(input: String): Int {
        val (rules, updates) = input.split("\n\n").map { it.split("\n").toList().filter { it != "" } }
        val rulePairs = rules.map {  it.split("|") }.map { (a, b) -> a.toInt() to b.toInt()  }
        var acc = 0
        for (update in updates) {
            val updateSteps = update.split(",").toList().map { it.toInt() }
            var middle = updateSteps.size / 2
            var good = true
            for (rule in rulePairs) {
                good = good && updateSteps.hasElementsInThisOrder(rule)
            }
            if (good) {
                acc += updateSteps[middle]
            }
        }
        return acc
    }

    private fun sort(list: MutableList<Int>, rules: List<Pair<Int, Int>>) {
        val filteredRules = rules.filter { it.first in list || it.second in list }
        val rulesToApply = ArrayDeque(filteredRules)
        while (rulesToApply.isNotEmpty()) {
            val rule = rulesToApply.removeFirst()
            val i = list.indexOf(rule.first)
            val j = list.indexOf(rule.second)
            if (i != -1 && j != -1 && i > j) {
                rulesToApply.addAll(filteredRules)
                val tmp = list[i]
                list[i] = list[j]
                list[j] = tmp
            }
        }
    }

    override fun part2(input: String): Int {
        val (rules, updates) = input.split("\n\n").map { it.split("\n").toList().filter { it != "" } }
        val rulePairs = rules.map {  it.split("|") }.map { (a, b) -> a.toInt() to b.toInt()  }
        var acc = 0
        for (update in updates) {
            val updateSteps = update.split(",").toList().map { it.toInt() }
            var middle = updateSteps.size / 2
            var good = true
            for (rule in rulePairs) {
                good = good && updateSteps.hasElementsInThisOrder(rule)
            }
            if (!good) {
                val mutableUpdateSteps = updateSteps.toMutableList()
                sort(mutableUpdateSteps, rulePairs)
                acc += mutableUpdateSteps[middle]
            }
        }
        return acc
    }
}

fun main() = Day05().run {
    println(part1(input))
    println(part2(input))
}