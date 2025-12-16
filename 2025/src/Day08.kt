import kotlin.math.sqrt

class Day08(val amount: Int = 1000) : Day<Int, Long>(year = 2025, day = 8) {

    override fun part1(input: String): Int {
        data class Vec3(val x: Int, val y: Int, val z: Int)
        val positions = input.lines().map { it.split(",").map { it.toInt() } }.map { (x,y,z) -> Vec3(x,y,z) }
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

    override fun part2(input: String): Long {
        data class Vec3(val x: Int, val y: Int, val z: Int)
        val positions = input.lines().map { it.split(",").map { it.toInt() } }.map { (x,y,z) -> Vec3(x,y,z) }
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
}

fun main() = Day08().run {
    println(part1(input))
    println(part2(input))
}