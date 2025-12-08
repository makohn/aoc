import kotlin.math.sqrt

fun main() {

    fun part1(input: List<String>, amount: Int = 10): Int {
        data class Vec3(val x: Int, val y: Int, val z: Int)
        val positions = input.map { it.split(",").map { it.toInt() } }.map { (x,y,z) -> Vec3(x,y,z) }
        data class Connection(val i: Int, val j: Int, val d: Double)
        val connections = ArrayList<Connection>()
        for ((i, v) in positions.withIndex()) for (j in i+1..positions.lastIndex) {
            val u = positions[j]
            val dist =
                sqrt(((v.x - u.x).toLong() * (v.x - u.x) + (v.y - u.y).toLong() * (v.y - u.y) + (v.z - u.z).toLong() * (v.z - u.z)).toDouble())
            connections.add(Connection(i, j, dist))
        }

        val candidates = connections.sortedBy { it.d }.take(amount)
        val circuits = HashMap<Int, Int>()
        var idx = 0
        for ((i, j) in candidates) {
            if (i in circuits.keys && j in circuits.keys) {
                val x = circuits[i]!!
                val y = circuits[j]!!
                if (x != y) {
                    val updates = circuits.filter { (_, v) -> v == y }.keys
                    for (u in updates) {
                        circuits[u] = x
                    }
                }
            } else if (i in circuits.keys) {
                circuits[j] = circuits[i]!!
            } else if (j in circuits.keys) {
                circuits[i] = circuits[j]!!
            } else {
                circuits[i] = idx
                circuits[j] = idx
                idx++
            }
        }
        val res = circuits.entries.groupBy { it.value }.values.map { it.size }.sortedDescending().take(3).reduce { a, b -> a * b }
        return res
    }

    fun part2(input: List<String>): Long {
        data class Vec3(val x: Int, val y: Int, val z: Int)
        val positions = input.map { it.split(",").map { it.toInt() } }.map { (x,y,z) -> Vec3(x,y,z) }
        data class Connection(val i: Int, val j: Int, val d: Double)
        val connections = ArrayList<Connection>()
        for ((i, v) in positions.withIndex()) for (j in i+1..positions.lastIndex) {
            val u = positions[j]
            val dist =
                sqrt(((v.x - u.x).toLong() * (v.x - u.x) + (v.y - u.y).toLong() * (v.y - u.y) + (v.z - u.z).toLong() * (v.z - u.z)).toDouble())
            connections.add(Connection(i, j, dist))
        }

        val circuits = HashMap<Int, Int>()
        for (c in positions.indices) {
            circuits[c] = c
        }
        var idx = 0
        for ((i, j) in connections.sortedBy { it.d }) {
            if (i in circuits.keys && j in circuits.keys) {
                val x = circuits[i]!!
                val y = circuits[j]!!
                if (x != y) {
                    val updates = circuits.filter { (_, v) -> v == y }.keys
                    for (u in updates) {
                        circuits[u] = x
                    }
                }
            } else if (i in circuits.keys) {
                circuits[j] = circuits[i]!!
            } else if (j in circuits.keys) {
                circuits[i] = circuits[j]!!
            } else {
                circuits[i] = idx
                circuits[j] = idx
                idx++
            }
            val groups = circuits.entries.groupBy { it.value }
            if (groups.size == 1) {
                val res = positions[i].x.toLong() * positions[j].x
                return res
            }
        }
        return 0
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 40)
    check(part2(testInput) == 25272L)

    val input = readInput("Day08")
    println(part1(input, 1000))
    println(part2(input))
}