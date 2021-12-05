package com.ionutciuta.aoc2021.day03

import com.ionutciuta.aoc2021.common.Challenge

class Day03 : Challenge("day3") {
    private fun getMostCommonDigit(lines: List<String>): List<Int> {
        return lines.map { line ->
            line.toCharArray().map { char -> char - '0' }
        }.reduce { acc, list ->
            acc.zip(list) { a, b -> a + b }
        }.map { count ->
            if (count > lines.size / 2) 1 else 0
        }
    }

    override fun solvePart1() {
        val lines = readLines()

        val gammaRate = getMostCommonDigit(lines)
        val epsilonRate = gammaRate.map { 1 - it }

        val gammaRateDec = gammaRate.map { it.toString() }.reduce { s, acc -> s + acc }.toInt(2)
        val epsilonRateDec = epsilonRate.map { it.toString() }.reduce { s, acc -> s + acc }.toInt(2)
        val power = gammaRateDec * epsilonRateDec

        println(power)
    }

    override fun solvePart2() {
        TODO("Not yet implemented")
    }
}

fun main(args: Array<String>) {
    val challenge = Day03()
    challenge.solvePart1()
    challenge.solvePart2()
}