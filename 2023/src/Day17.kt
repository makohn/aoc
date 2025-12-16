class Day17 : Day<Int, Int>(year = 2023, day = 17){

    override fun part1(input: String): Int {
        val map = input.lines().toCharMatrix()
        val (n, m) = map.dimension

        data class Node(val x: Int, val y: Int, val d: Direction, val s: Int)

        val start = Node(0, 0, Direction.East, 1)
        val res = dijkstra(start) { cell ->
            val (x, y, currentDir, steps) = cell
            Direction.entries
                .filter { it.ordinal != 3 - currentDir.ordinal }
                // xDir and yDir are vice-versa in the Direction enum
                .map { dir -> Node(x+dir.yDir, y+dir.xDir, dir, if (dir == currentDir) steps+1 else 1) }
                .filter { it.x in 0..<n && it.y in 0..<m && it.s <= 3 }
                .map { it to map[it.x][it.y].digitToInt() }
        }

        return res
            .filter { (k, _) -> k.x == n-1 && k.y == m-1 }
            .minBy { it.value }
            .value
    }

    override fun part2(input: String): Int {
        val map = input.lines().toCharMatrix()
        val (n, m) = map.dimension

        data class Node(val x: Int, val y: Int, val d: Direction, val s: Int)

        val start = Node(0, 0, Direction.East, 0)
        val res = dijkstra(start) { cell ->
            val (x, y, currentDir, steps) = cell
            Direction.entries
                .filter { if (steps < 4) it == currentDir else it.ordinal != 3 - currentDir.ordinal }
                // xDir and yDir are vice-versa in the Direction enum
                .map { dir -> Node(x+dir.yDir, y+dir.xDir, dir, if (dir == currentDir) steps+1 else 1) }
                .filter { it.x in 0..<n && it.y in 0..<m && steps <= 10}
                .map { it to map[it.x][it.y].digitToInt() }
        }

        return res
            .filter { (k, _) -> k.x == n-1 && k.y == m-1 && k.s >= 4}
            .minBy { it.value }
            .value
    }
}

fun main() = Day17().run {
    println(part1(input))
    println(part2(input))
}