package year2023

import util.core.*
import util.grid.*

class Day13 : Solution<Int, Int> {

    override fun part1(input: String): Int {
        fun verticalMirrorPosition(pattern: CharGrid): Int {
            val (width, height) = pattern.shape
            moveMirror@for (mirror in 1..<width) {
                for (margin in 0..width) {
                    val left = mirror - 1 - margin
                    val right = mirror + margin
                    if (left in 0..<mirror && right in mirror..<width) {
                        for (row in 0..<height) {
                            if (pattern[row][left] != pattern[row][right]) continue@moveMirror
                        }
                    }
                }
                return mirror
            }
            return 0
        }

        fun horizontalMirrorPosition(pattern: CharGrid): Int {
            val (width, height) = pattern.shape
            moveMirror@for (mirror in 1..<height) {
                for (margin in 0..height) {
                    val above = mirror - 1 - margin
                    val below = mirror + margin
                    if (above in 0..<mirror && below in mirror..<height) {
                        for (col in 0..<width) {
                            if (pattern[above][col] != pattern[below][col]) continue@moveMirror
                        }
                    }
                }
                return mirror
            }
            return 0
        }

        return input
            .split("\n\n")
            .map { it.split("\n").toCharGrid() }
            .sumOf { verticalMirrorPosition(it) + 100 * horizontalMirrorPosition(it) }
    }

    override fun part2(input: String): Int {
        fun verticalMirrorPosition(pattern: CharGrid): Int {
            val (width, height) = pattern.shape
            for (mirror in 1..<width) {
                var error = 0
                for (margin in 0..width) {
                    val left = mirror - 1 - margin
                    val right = mirror + margin
                    if (left in 0..<mirror && right in mirror..<width) {
                        for (row in 0..<height) {
                            if (pattern[row][left] != pattern[row][right]) error++
                        }
                    }
                }
                if (error == 1) return mirror
            }
            return 0
        }

        fun horizontalMirrorPosition(pattern: CharGrid): Int {
            val (width, height) = pattern.shape
            for (mirror in 1..<height) {
                var error = 0
                for (margin in 0..height) {
                    val above = mirror - 1 - margin
                    val below = mirror + margin
                    if (above in 0..<mirror && below in mirror..<height) {
                        for (col in 0..<width) {
                            if (pattern[above][col] != pattern[below][col]) error++
                        }
                    }
                }
                if (error == 1) return mirror
            }
            return 0
        }

        return input
            .split("\n\n")
            .map { it.split("\n").toCharGrid() }
            .sumOf { verticalMirrorPosition(it) + 100 * horizontalMirrorPosition(it) }
    }
}
