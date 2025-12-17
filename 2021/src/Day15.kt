import aoc.algorithm.dijkstra

class Day15 : Day<Int, Int>(year = 2021, day = 15) {

    override fun part1(input: String): Int {
        val board = parse(input)
        val n = board.size
        val m = board[0].size
        return solve(board, n, m)
    }

    override fun part2(input: String): Int {
        val board = parse(input)
        val n = board.size
        val m = board[0].size
        val map = Array(n*5) { IntArray(m*5) }
        for (i in board.indices) {
            for (j in board[i].indices) {
                for (k in 0..4) {
                    val l = j + k*m
                    map[i][l] = (((board[i][j] + 1*k -1)) % 9) + 1
                }
            }
            for (x in 1..4) {
                map[i + x*n] = map[i].map { ((it + x*1 -1)) % 9 + 1 }.toIntArray()
            }
        }
        return solve(map, n*5, m*5)
    }

    private fun parse(input: String) =
        input.lines().map { it.map { c -> c.digitToInt() }.toIntArray() }.toTypedArray()

    private fun solve(board: Array<IntArray>, n: Int, m: Int): Int {

        fun adjacent(u: Int2) = buildList {
            val (i, j) = u
            for ((ii, jj) in listOf(Int2(i-1, j), Int2(i+1, j), Int2(i, j-1), Int2(i, j+1))) {
                if ((0 <= ii) && (ii < m) && (0 <= jj) && (jj < n)) {
                    add(Int2(ii, jj) to board[ii][jj])
                }
            }
        }

        val start = Int2(0, 0)
        val end = Int2(m-1, n-1)

        return dijkstra(start, ::adjacent)[end]!!
    }
}

fun main() = Day15().run {
    println(part1(input))
    println(part2(input))
}