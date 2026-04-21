package year2020

import util.core.*

class Day04 : Solution<Int, Int> {

    private data class Data(val key: String, val value: String)

    private fun validate(key: String, value: String): Boolean = when (key) {
        "byr" -> value.toInt() in 1920..2002
        "iyr" -> value.toInt() in 2010..2020
        "eyr" -> value.toInt() in 2020..2030
        "hgt" -> when {
            value.takeLast(2) == "in" -> value.dropLast(2).toInt() in 59..76
            value.takeLast(2) == "cm" -> value.dropLast(2).toInt() in 150..193
            else -> false
        }

        "hcl" -> value.length == 7 && value[0] == '#' && value.drop(1).all { it in '0'..'9' || it in 'a'..'f' }
        "ecl" -> value in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        "pid" -> value.length == 9 && value.all { c -> c.isDigit() }
        else -> false
    }

    private fun parse(passport: String): List<Data> = passport
        .split(" ", "\n", ":")
        .chunked(2)
        .map { (k, v) -> Data(k, v) }
        .filter { it.key != "cid" }

    override fun part1(input: String): Int = input
        .split("\n\n")
        .map { parse(it) }
        .count { it.size == 7 }

    override fun part2(input: String): Int = input
        .split("\n\n")
        .map { parse(it) }
        .count { it.size == 7 && it.all { (k, v) -> validate(k, v) } }
}
