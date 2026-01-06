package year2021

import util.core.*

class Day25 : Solution<Int, Int>(year = 2021, day = 25) {

    override fun part1(input: String): Int {
        var mat = input.lines().map { it.toCharArray() }.toTypedArray()
        val newMat = mat.map { it.copyOf() }.toTypedArray()
        var step = 0

        do {
            step++
            mat = newMat.map { it.copyOf() }.toTypedArray()
            for (i in mat.indices) {
                for (j in mat[0].indices) {
                    val k = if (j < mat[0].lastIndex) j+1 else 0
                    if (mat[i][j] == '>' && newMat[i][k] == '.' && mat[i][k] != '>') {
                        newMat[i][j] = '.'
                        newMat[i][k] = '>'
                    }
                }
            }
            for (i in mat.indices) {
                for (j in mat[0].indices) {
                    val k = if (i < mat.lastIndex) i+1 else 0
                    if (mat[i][j] == 'v' && newMat[k][j] == '.' && mat[k][j] != 'v') {
                        newMat[i][j] = '.'
                        newMat[k][j] = 'v'
                    }
                }
            }
        } while (!(mat.contentDeepEquals(newMat)))
        newMat.forEach { println(it.joinToString("") ) }
        println(step)
        return step
    }

    override fun part2(input: String) = 0
}

fun main() = Day25().run {
    println(part1(input))
}