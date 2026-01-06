package model

import kotlinx.serialization.Serializable

@Serializable
data class Answers(val answer: Map<Int, Answer>)

@Serializable
data class Answer(
    val name: String?,
    val part1: String,
    val part2: String,
    val test1: String? = null,
    val test2: String? = null
)