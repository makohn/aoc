package year2022

import util.core.*
import util.math.*
import util.parse.*

class Day19 : Solution<Int, Int>(year = 2022, day = 19) {

    private data class ResourceBundle(
        var ore: Int,
        var clay: Int,
        var obsidian: Int,
        var geode: Int,
    ) {
        operator fun plus(other: ResourceBundle) = ResourceBundle(
            ore = this.ore + other.ore,
            clay = this.clay + other.clay,
            obsidian = this.obsidian + other.obsidian,
            geode = this.geode + other.geode,
        )

        operator fun minus(other: ResourceBundle) = ResourceBundle(
            ore = this.ore - other.ore,
            clay = this.clay - other.clay,
            obsidian = this.obsidian - other.obsidian,
            geode = this.geode - other.geode,
        )

        operator fun plusAssign(other: ResourceBundle) {
            this.ore += other.ore
            this.clay += other.clay
            this.obsidian += other.obsidian
            this.geode += other.geode
        }

        infix fun leq(other: ResourceBundle): Boolean = this.ore <= other.ore && this.clay <= other.clay && this.obsidian <= other.obsidian
    }

    private data class Blueprint(
        val id: Int,
        val maxOre: Int,
        val maxClay: Int,
        val maxObsidian: Int,
        val oreCost: ResourceBundle,
        val clayCost: ResourceBundle,
        val obsidianCost: ResourceBundle,
        val geodeCost: ResourceBundle,
    )

    private fun Blueprint(input: List<Int>): Blueprint {
        val (id, ore1, ore2, ore3, clay, ore4, obsidian) = input
        return Blueprint(
            id = id,
            maxOre = maxOf(ore1, ore2, ore3, ore4),
            maxClay = clay,
            maxObsidian = obsidian,
            oreCost = ResourceBundle(ore1, 0, 0, 0),
            clayCost = ResourceBundle(ore2, 0, 0, 0),
            obsidianCost = ResourceBundle(ore3, clay, 0, 0),
            geodeCost = ResourceBundle(ore4, 0, obsidian, 0),
        )
    }

    class Result(value: Int) {
        var value = value
            private set
        fun set(newValue: Int) {
            value = newValue
        }
    }

    companion object {
        private val ZERO = ResourceBundle(0, 0, 0, 0)
        private val ORE_BOT = ResourceBundle(1, 0, 0, 0)
        private val CLAY_BOT = ResourceBundle(0, 1, 0, 0)
        private val OBSIDIAN_BOT = ResourceBundle(0, 0, 1, 0)
        private val GEODE_BOT = ResourceBundle(0, 0, 0, 1)
    }

    private fun maximize(blueprint: Blueprint, time: Int): Int {
        val result = Result(0)
        dfs(blueprint, result, time, ORE_BOT, ZERO)
        return result.value
    }

    private fun dfs(blueprint: Blueprint, result: Result, time: Int, bots: ResourceBundle, resources: ResourceBundle) {
        result.set(maxOf(result.value, resources.geode + bots.geode * time))
        if (canBeatBest(blueprint, result, time, bots.copy(), resources.copy())) {
            if (bots.obsidian > 0 && time > 1) {
                step(blueprint, result, time, bots.copy(), resources.copy(), GEODE_BOT, blueprint.geodeCost)
            }
            if (bots.obsidian < blueprint.maxObsidian && bots.clay > 0 && time > 3) {
                step(blueprint, result, time, bots.copy(), resources.copy(), OBSIDIAN_BOT, blueprint.obsidianCost)
            }
            if (bots.ore < blueprint.maxOre && time > 3) {
                step(blueprint, result, time, bots.copy(), resources.copy(), ORE_BOT, blueprint.oreCost)
            }
            if (bots.clay < blueprint.maxClay && time > 5) {
                step(blueprint, result, time, bots.copy(), resources.copy(), CLAY_BOT, blueprint.clayCost)
            }
        }
    }

    private fun canBeatBest(
        blueprint: Blueprint,
        result: Result,
        time: Int,
        bots: ResourceBundle,
        resources: ResourceBundle,
    ): Boolean {
        repeat(time) {
            resources.ore = blueprint.maxOre
            if (blueprint.geodeCost leq resources) {
                resources += bots - blueprint.geodeCost
                bots += GEODE_BOT
            } else if (blueprint.obsidianCost leq resources) {
                resources += bots - blueprint.obsidianCost
                bots += OBSIDIAN_BOT
            } else {
                resources += bots
            }

            bots += CLAY_BOT
        }

        return resources.geode > result.value
    }

    private fun step(
        blueprint: Blueprint,
        result: Result,
        time: Int,
        bots: ResourceBundle,
        resources: ResourceBundle,
        newBot: ResourceBundle,
        cost: ResourceBundle,
    ) {
        for (jump in 1..<time) {
            if (cost leq resources) {
                dfs(blueprint, result, time - jump, bots + newBot, resources + bots - cost)
                break
            }
            resources += bots
        }
    }

    private fun parse(input: String) = input.extractInts().chunked(7).map(::Blueprint)

    override fun part1(input: String): Int = parse(input).sumOf { it.id * maximize(it, 24) }

    override fun part2(input: String): Int = parse(input).take(3).map { maximize(it, 32) }.product()
}
