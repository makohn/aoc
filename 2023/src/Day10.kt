import util.algorithm.bfs

class Day10 : Day<Int, Int>(year = 2023, day = 10) {

    fun getNeighbours(input: String): Pair<CharPoint, Map<CharPoint, List<CharPoint>>> {
        val map = input.lines().toCharArray2()
        val (n, m) = map.size2

        lateinit var startCell: CharPoint
        val neighbours = mutableMapOf<CharPoint, List<CharPoint>>()

        for (i in 0..<n) for (j in 0..<m) {
            val currentCell = CharPoint(i, j, map[i][j])
            when (currentCell.data) {
                '|' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.North, Direction.South)
                '-' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.East, Direction.West)
                'L' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.North, Direction.East)
                'J' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.North, Direction.West)
                '7' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.South, Direction.West)
                'F' -> neighbours[currentCell] = map.neighborsOf(currentCell, Direction.South, Direction.East)
                'S' -> startCell = currentCell
            }
        }
        neighbours[startCell] = neighbours.filter { (_, v) -> v.any { it == startCell } }.keys.toList()
        return startCell to neighbours
    }

    fun getMainLoopWithDistance(startCell: CharPoint, neighbours: Map<CharPoint, List<CharPoint>>): HashMap<CharPoint, Int> {
        return bfs(startCell) { neighbours[it]!! }
    }

    override fun part1(input: String): Int {
        val (startCell, neighbours) = getNeighbours(input)
        return getMainLoopWithDistance(startCell, neighbours).maxBy { it.value }.value
    }

    override fun part2(input: String): Int {
        val (startCell, neighbours) = getNeighbours(input)
        val mainLoop = getMainLoopWithDistance(startCell, neighbours)
        val map = input.lines().toCharArray2()
        val cleanMap = map
            .mapIndexed { i, row ->
                row.mapIndexed { j, c -> CharPoint(i, j, c) }.map {
                    if (it.data == 'S') {
                        val n = neighbours[it]!!.map { it.data }.toSet()
                        when {
                            n == setOf('-') || n == setOf('F', '7') || n == setOf('L', 'J') -> '-'
                            else -> '|'
                        }
                    }
                    else if (it in mainLoop) it.data else '.'
                }
            }

        var insideCount = 0

        for (row in cleanMap) {
            var parity = 0
            var previous = '.'
            for (char in row) {
                when (char) {
                    '.' -> if (parity%2 != 0) insideCount++
                    in "JLF7|" -> {
                        when(previous to char) {
                            'F' to 'J', 'L' to '7' -> Unit
                            else -> parity++
                        }
                        previous = char
                    }
                }
            }
        }
        return insideCount
    }
}

fun main() = Day10().run {
    println(part1(input))
    println(part2(input))
}