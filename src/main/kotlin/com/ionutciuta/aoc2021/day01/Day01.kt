package com.ionutciuta.aoc2021.day01

import com.ionutciuta.aoc2021.common.Challenge

class Day01: Challenge("day1") {

    override fun solvePart1() {
        val r = readLinesAsInts()
                .zipWithNext { first, second ->  second - first }
                .count { it > 0 }
        println(r)
    }

    override fun solvePart2() {
        val windowsSize = 3
        val lines = readLinesAsInts()
        val max = lines.size - windowsSize - 1

        var count = 0;
        for (i in 0..max) {
            val next = i + 1
            if(lines.subList(i, i + windowsSize).sum() < lines.subList(next, next + windowsSize).sum())
                count++
        }
        print(count)
    }
}

fun main(args: Array<String>) {
    val challenge = Day01()
    challenge.solvePart1()
    challenge.solvePart2()
}