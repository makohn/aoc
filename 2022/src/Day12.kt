import util.algorithm.bfs

class Day12 : Day<Int, Int>(year = 2022, day = 12) {

    fun solve(input: String, predicate: (Int) -> Boolean, startWith: Char, vararg endWith: Char): Int {
        val inputMatrix = input.lines().toCharArray2()
        val dataPoints = inputMatrix.dataPoints()
        val startNode = dataPoints.single { it.data == startWith }

        fun Char.height() = when(this) {
            'S' -> 0
            'E' -> 'z' - 'a'
            else -> this - 'a'
        }

        val directions = arrayOf(Direction.North, Direction.East, Direction.South, Direction.West)
        val visited = bfs(startNode) { p ->
            inputMatrix.neighborsOf(p, *directions).filter { predicate(it.data.height() - p.data.height()) }
        }

        return visited.filter { it.key.data in endWith }.minBy { it.value }.value
    }

    override fun part1(input: String): Int {
        return solve(input, { diff -> diff <= 1 }, 'S', 'E')
    }

    override fun part2(input: String): Int {
        return solve(input, { diff -> diff >= -1 }, 'E', 'a', 'S')
    }
}

fun main() = Day12().run {
    println(part1(input))
    println(part2(input))
}