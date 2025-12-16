typealias Image = Set<Pair<Int, Int>>
typealias Bounds = List<Int>

class Day20 : Day<Int, Int>(year = 2021, day = 20) {

    override fun part1(input: String): Int {
        val (algorithm, image) = parse(input)
        return runEnhancements(image, algorithm, 2)
    }

    override fun part2(input: String): Int {
        val (algorithm, image) = parse(input)
        return runEnhancements(image, algorithm, 50)
    }

    fun runEnhancements(baseImage: Image, algorithm: String, count: Int): Int {
        var image = baseImage
        var bounds = image.bounds().apply(padding = count*4)
        repeat(count) {
            image = enhance(image, algorithm, bounds)
            bounds = bounds.apply(padding = -3)
        }
        return image.count()
    }

    fun parse(input: String): Pair<String, Image> {
        val inputLines = input.lines()
        val algorithm = inputLines.first().also { check(it.length == 512) }
        val image = inputLines.drop(2).flatMapIndexed { i, row ->
            row.indicesOf('#').map { i to it }
        }.toSet()
        return algorithm to image
    }

    fun visualize(image: Image, padding: Int = 0) {
        val (minX, maxX, minY, maxY) = image.bounds()
        println("=".repeat(maxX - minX + padding*2 + 1))
        (minX - padding .. maxX + padding).map { x ->
            (minY - padding .. maxY+ padding).map { y ->
                x to y }.toList() }
            .forEach {
                it.forEach { p -> print(if (image.contains(p)) "#" else ".")}
                println()
            }
    }

    private fun enhance(image: Image, algorithm: String, bounds: Bounds): Image {
        val (minX, maxX, minY, maxY) = bounds
        val outputImage = mutableSetOf<Pair<Int, Int>>()
        (minX .. maxX).forEach { x ->
            (minY ..maxY).forEach { y ->
                var sum = 0
                for (i in (-1..1)) {
                    for (j in (-1..1)) {
                        sum = (sum shl 1) + ((x + i to y + j) in image).compareTo(false)
                    }
                }
                if (algorithm[sum] == '#') outputImage.add(x to y)
            }
        }
        return outputImage
    }

    fun String.indicesOf(char: Char) = mapIndexed { i, c -> if (c == char) i else null }.filterNotNull()
    fun Image.bounds(): Bounds {
        val minX = this@bounds.minOf { it.first }
        val maxX = this@bounds.maxOf { it.first }
        val minY = this@bounds.minOf { it.second }
        val maxY = this@bounds.maxOf { it.second }
        return listOf(minX, maxX, minY, maxY)
    }
    fun Bounds.apply(padding: Int) = listOf(
        this@apply[0] - padding,
        this@apply[1] + padding,
        this@apply[2] - padding,
        this@apply[3] + padding
    )
}

fun main() = Day20().run {
    println(part1(input))
    println(part2(input))
}