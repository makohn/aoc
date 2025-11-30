
fun main() {

    val day = "05"

    fun List<Int>.hasElementsInThisOrder(pair: Pair<Int, Int>): Boolean {
        val i = indexOf(pair.first)
        val j = indexOf(pair.second)
        return i <= j || i == -1 || j == -1
    }

    fun part1(input: String): Int {
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

    fun sort(list: MutableList<Int>, rules: List<Pair<Int, Int>>) {
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

    fun part2(input: String): Int {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInputString("Day${day}_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInputString("Day${day}")
    println(part1(input))
    println(part2(input))
}