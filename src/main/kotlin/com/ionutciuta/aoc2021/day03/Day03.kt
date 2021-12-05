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

    private fun binaryListToDec(binary: List<Int>): Int =
            binary.map { it.toString() }.reduce { s, acc -> s + acc }.toInt(2)

    override fun solvePart1() {
        val lines = readLines()

        val gammaRate = getMostCommonDigit(lines)
        val epsilonRate = gammaRate.map { 1 - it }

        val gammaRateDec = binaryListToDec(gammaRate)
        val epsilonRateDec = binaryListToDec(epsilonRate)
        val power = gammaRateDec * epsilonRateDec

        println(power)
    }

    private fun findRating(lines: List<List<Int>>, i: Int, target: Int, filter: (Int, Int) -> Boolean): List<Int> {
        val count = lines.map { it[i] }.sum()

        val filteredLines = if (count * 1.0 == lines.size / 2.0) {
            lines.filter { it[i] == target }
        } else {
            val mostCommon = if (count > lines.size / 2.0) 1 else 0
            lines.filter { filter(it[i], mostCommon) }
        }

        return if (filteredLines.size == 1) {
            filteredLines[0]
        } else {
            val next = i + 1
            findRating(filteredLines, next, target, filter)
        }
    }

    private fun findO2Rating(lines: List<List<Int>>): List<Int> =
            findRating(lines, 0, 1) { a, b -> a == b }

    private fun findCo2Rating(lines: List<List<Int>>): List<Int> =
            findRating(lines, 0, 0) { a, b -> a != b }

    override fun solvePart2() {
        val lines = readLines().map { it.toCharArray().map { char -> char - '0' } }
        val o2Rating = binaryListToDec(findO2Rating(lines))
        val co2Rating = binaryListToDec(findCo2Rating(lines))
        val rating = o2Rating * co2Rating
        println(rating)
    }
}

fun main(args: Array<String>) {
    val challenge = Day03()
    challenge.solvePart1()
    challenge.solvePart2()
}