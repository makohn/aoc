fun main() {

    fun part1(input: List<String>): Int {
        val connections = input.map { it.split(":") }.associate { (m, t) -> m to t.trim().split(" ") }
        val start = connections["you"]!!

        var count = 0
        val q = ArrayList<List<String>>()
        q.add(start)
        while (q.isNotEmpty()) {
            val targets = q.removeFirst()
            for (target in targets) {
                if (target == "out") count++
                else q.add(connections[target]!!)
            }
        }

        return count
    }

    fun part2(input: List<String>): Long {
        val connections = input.map { it.split(":") }.associate { (m, t) -> m to t.trim().split(" ") }
        data class Node(val value: String, val dac: Boolean, val fft: Boolean)

        val cache = HashMap<Node, Long>()
        fun findPath(node: Node): Long {
            if (node in cache) return cache[node]!!
            if (node.value == "out") return if (node.dac && node.fft) 1 else 0
            var count = 0L
            val targets = connections[node.value]!!
            for (target in targets) {
                count += findPath(Node(target, if (target == "dac") true else node.dac, if (target == "fft") true else node.fft))
            }
            cache[node] = count
            return count
        }

        return findPath(Node("svr", dac = false, fft = false))
    }

    val testInput = readInput("Day11_test")
    check(part1(testInput) == 5)
    val testInput2 = readInput("Day11_test2")
    check(part2(testInput2) == 2L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}