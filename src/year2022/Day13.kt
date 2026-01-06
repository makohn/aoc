package year2022

import util.Solution

class Day13 : Solution<Int, Int>(year = 2022, day = 13) {

    fun eval(str: MutableList<Char>): List<Any> {
        val list = mutableListOf<Any>()
        var cs = ""
        while (str.isNotEmpty()) {
            when (val c = str.removeFirst()) {
                in '0'..'9' -> cs+=c
                '[' -> list.add(eval(str))
                ']' -> {
                    if (cs.isNotEmpty()) list.add(cs.toInt())
                    return list
                }
                ',' -> {
                    if (cs.isNotEmpty()) list.add(cs.toInt())
                    cs = ""
                }
                else -> error("Unexpected!")
            }
        }
        return list
    }

    fun compare(a: Any, b: Any): Int {
        if (a is Int && b is Int) {
            return a.compareTo(b)
        }

        val l = if (a is List<*>) a else listOf(a)
        val r = if (b is List<*>) b else listOf(b)

        for (i in l.indices) {
            if (i !in r.indices) return 1
            val res = compare(l[i]!!, r[i]!!)
            if (res != 0) return res
        }
        return l.size.compareTo(r.size)
    }

    override fun part1(input: String): Int {
        val numRightOrder = input
            .lineSequence()
            .filter { it.isNotEmpty() }
            .chunked(2)
            .map {
                val (a, b) = it
                eval(a.toMutableList())[0] as List<*> to eval(b.toMutableList())[0] as List<*>
            }
            .map { (a, b) ->  compare(a, b) }
            .foldIndexed(0) { idx, acc, b -> if (b < 0) acc + idx + 1 else acc }

        return numRightOrder
    }

    override fun part2(input: String): Int {
        val list = input
            .lineSequence()
            .filter { it.isNotEmpty() }
            .map { eval(it.toMutableList())[0] as List<*> }
            .toMutableList()

        println(list)

        val da = listOf(listOf(2))
        val db = listOf(listOf(6))

        list.add(da)
        list.add(db)

        list.sortWith(::compare)

        val a = list.indexOf(da) + 1
        val b = list.indexOf(db) + 1

        return a * b
    }
}

fun main() = Day13().run {
    println(part1(input))
    println(part2(input))
}