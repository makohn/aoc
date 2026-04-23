package year2020

import util.core.*

class Day07 : Solution<Int, Int> {

    override fun part1(input: String): Int {
        val bags = HashMap<String, ArrayList<String>>()
        for (line in input.lines()) {
            val rule = line.split(" ").chunked(4)
            val key = rule[0][0] + rule[0][1]
            val rules = rule.drop(1).filter { it[0] != "no" }.map { it[1] + it[2] }
            bags.computeIfAbsent(key) { ArrayList() }.addAll(rules)
        }

        val seen = HashMap<String, Boolean>()
        fun containsShinyGold(bag: String): Boolean {
            if (bag in seen) return seen[bag]!!
            val rules = bags[bag]!!
            if ("shinygold" in rules) {
                seen[bag] = true
                return true
            }
            for (rule in rules) {
                if (containsShinyGold(rule)) {
                    seen[bag] = true
                    return true
                }
            }
            seen[bag] = false
            return false
        }
        for (bag in bags.keys) {
            containsShinyGold(bag)
        }
        return seen.count { it.value }
    }

    override fun part2(input: String): Int = 0
}
