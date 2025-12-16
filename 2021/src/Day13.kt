import aoc.fromAsciiArt

class Day13 : Day<Int, String>(year = 2021, day = 13) {

    companion object {
        private const val prefix = "fold along "
        private const val neutral = 0
    }

    override fun part1(input: String): Int {
        val (dotInstructions, foldInstructions) = parse(input)
        return fold(dotInstructions, foldInstructions, times = 1).first
    }

    override fun part2(input: String): String {
        val (dotInstructions, foldInstructions) = parse(input)
        return fold(dotInstructions, foldInstructions).second
    }

    private fun parse(input: String): Pair<List<MutableList<Int>>, List<Pair<Int, Int>>> {
        val inputLines = input.lines()
        val splitIndex = inputLines.indexOf("")
        val dotInstructions = inputLines.subList(0, splitIndex)
            .map { it.split(",").map(String::toInt).toMutableList() }
        val foldInstructions = inputLines.subList(splitIndex+1, inputLines.size)
            .map { it.removePrefix(prefix).split("=") }
            .map {
                if (it.first() == "y") neutral to it.last().toInt()
                else it.last().toInt() to neutral
            }
        return dotInstructions to foldInstructions
    }

    private fun fold(dotInstructions: List<MutableList<Int>>, foldInstructions: List<Pair<Int, Int>>,
             times: Int = foldInstructions.size): Pair<Int, String> {
        var transparent = dotInstructions.toMutableSet()
        var width = transparent.maxOf { it.first() }
        var height = transparent.maxOf { it.last() }
        foldInstructions.forEachIndexed { i, fold ->
            transparent = transparent.map { dot ->
                dot[0] = if ((fold.first != 0) and (dot.first() > fold.first)) width - dot[0] - (fold.first - width/2) else dot[0]
                dot[1] = if ((fold.second != 0) and (dot.last() > fold.second)) height - dot[1] - (fold.second - height/2) else dot[1]
                dot
            }.filter { dot ->
                (dot.first() >= 0) and (dot.last() >= 0)
            }.toMutableSet()
            width -= if (fold.first != 0) (width - fold.first + 1) else 0
            height -= if (fold.second != 0) (height - fold.second + 1) else 0
            val patterns = visualize(transparent, width, height)
            if (i >= times-1) return@fold transparent.count() to patterns.fromAsciiArt()
        }
        return -1 to ""
    }

    private fun visualize(dots: Set<MutableList<Int>>, width: Int, height: Int): String {
        val transparent = mutableListOf<MutableList<Char>>()
        (0 .. height).forEach { _ -> transparent.add(".".repeat(width+1).toMutableList()) }
        dots.forEach { dot -> transparent[dot.last()][dot.first()] = '#' }
        return transparent.joinToString("\n") { it.joinToString("") }
    }
}

fun main() = Day13().run {
    println(part1(input))
    println(part2(input))
}