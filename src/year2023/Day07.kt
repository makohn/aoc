package year2023

import util.Solution

class Day07 : Solution<Int, Int>(year = 2023, day = 7) {

    data class Hand(val cards: String, val bid: Int)

    internal enum class HandType(val cardDistribution: List<Int>) {
        FiveOfAKind(listOf(5)),
        FourOfAKind(listOf(1, 4)),
        FullHouse(listOf(2, 3)),
        ThreeOfAKind(listOf(1, 1, 3)),
        TwoPair(listOf(1, 2, 2)),
        OnePair(listOf(1, 1, 1, 2)),
        HighCard(listOf(1, 1, 1, 1, 1));
    }

    internal fun List<Int>.toHandType() = HandType.entries.firstOrNull { this == it.cardDistribution }

    override fun part1(input: String): Int {
        val parsed = input
            .lines()
            .map { it.split(" ") }
            .map { (a, b) -> Hand(a, b.toInt()) }

        val handTypes = parsed.map { hand -> hand to hand.cards
            .groupingBy { it }
            .eachCount()
            .values
            .sorted()
            .toHandType()
        }

        val sorted = handTypes.sortedWith(
            compareByDescending<Pair<Hand, HandType?>> { (_, handType) -> handType }
                .thenBy { (hand, _) ->
                    hand.cards
                        .replace('A', 'Z')
                        .replace('K', 'Y')
                        .replace('Q', 'X')
                        .replace('J', 'W')
                        .replace('T', 'V')
                })

        return sorted.withIndex().sumOf { (idx, pair) -> (idx+1) * pair.first.bid }
    }

    override fun part2(input: String): Int {
        val parsed = input
            .lines()
            .map { it.split(" ") }
            .map { (a, b) -> Hand(a, b.toInt()) }

        val handTypes = parsed.map { hand -> hand to hand.cards
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .toMap()
            .toMutableMap()
        }.map numCards@{ (hand, cardCount) ->
            val first = cardCount.toList().firstOrNull { it.first != 'J' } ?: return@numCards hand to HandType.FiveOfAKind
            val joker = cardCount['J']
            cardCount.replace(first.first, first.second + (joker?:0))
            cardCount.remove('J')
            val handType = cardCount.values.sorted().toHandType()
            hand to handType
        }

        val sorted = handTypes.sortedWith(
            compareByDescending<Pair<Hand, HandType?>> { (_, handType) -> handType }
                .thenBy { (hand, _) ->
                    hand.cards
                        .replace('A', 'Z')
                        .replace('K', 'Y')
                        .replace('Q', 'X')
                        .replace('T', 'W')
                        .replace('J', '1')
                })

        return sorted.withIndex().sumOf { (idx, pair) -> (idx+1) * pair.first.bid }
    }
}

fun main() = Day07().run {
    println(part1(input))
    println(part2(input))
}