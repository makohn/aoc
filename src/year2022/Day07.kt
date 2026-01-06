package year2022

import util.Solution

class Day07 : Solution<Int, Int>(year = 2022, day = 7) {

    class TreeNode<T>(val name: String, val value: T, val parent: TreeNode<T>? = null) {
        val children = mutableListOf<TreeNode<T>>()

        fun add(child: TreeNode<T>) = children.add(child)
    }

    fun TreeNode<Int>.getDirSize(): Int {
        return children.fold(0) { acc, treeNode ->
            acc + if (treeNode.value == -1) treeNode.getDirSize() else treeNode.value
        }
    }

    fun getDirs(input: String): List<TreeNode<Int>> {
        val tree = TreeNode("/", -1)
        val dirs = mutableListOf(tree)
        var curDir = tree
        for (command in input.lines()) {
            val c = command.split(" ")
            when (c[0]) {
                "$" -> when (c[1]) {
                    "cd" -> curDir = if (c[2] == "..") curDir.parent!! else {
                        val newDir = TreeNode(c[1], -1, curDir)
                        dirs.add(newDir)
                        curDir.add(newDir)
                        newDir
                    }
                }
                "dir" -> Unit
                else -> curDir.add(TreeNode(c[1], c[0].toInt(), curDir))
            }
        }
        return dirs
    }

    override fun part1(input: String) = getDirs(input)
            .map { it.getDirSize() }
            .filter { it <= 100_000 }
            .sum()

    override fun part2(input: String): Int {
        val dirs = getDirs(input)
        val root = dirs.first { it.name == "/" }
        val used = root.getDirSize()
        val total = 70_000_000
        val needed = 30_000_000
        val actuallyNeeded = needed - (total - used)
        return dirs
            .map { it.getDirSize() }
            .filter { it >= actuallyNeeded }
            .min()
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}