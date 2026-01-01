import util.fromAsciiArt

class Day10 : Day<Int, String>(year = 2022, day = 10) {

    override fun part1(input: String): Int {
        var acc = 0
        var x = 1
        var cycle = 0

        fun tick() {
            cycle++
            if (cycle in (20 .. 220) step 40) {
                acc += cycle * x
            }
        }

        for (parts in input.lines().map { it.split(" ") }) {
            tick()
            val op = parts[0]
            when (op) {
                "addx" -> {
                    tick()
                    x+= parts[1].toInt()
                }
                "noop" -> Unit
            }
        }
        return acc
    }

    override fun part2(input: String): String {
        var x = 1
        var cycle = 0
        var cursor = 0
        val sb = StringBuilder()

        fun tick() {
            cycle++
            val crt = if (cursor in x-1..x+1) "#" else "."
            sb.append(crt)
            cursor++
            if (cursor == 40) {
                sb.appendLine()
                cursor = 0
            }
        }

        for (parts in input.lines().map { it.split(" ") }) {
            tick()
            val op = parts[0]
            if (op == "addx") {
                tick()
                x += parts[1].toInt()
            }
        }
        return sb.toString().trim().fromAsciiArt()
    }
}

fun main() = Day10().run {
    println(part1(input))
    println(part2(input))
}