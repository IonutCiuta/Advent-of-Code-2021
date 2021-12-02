package com.ionutciuta.aoc2021.day02

import com.ionutciuta.aoc2021.common.Challenge
import com.ionutciuta.aoc2021.day01.Day01

class Day02: Challenge("day2") {
    val FWD = "forward"
    val DOWN = "down"
    val UP = "up"

    override fun solvePart1() {
        var x = 0
        var y = 0
        readLines()
                .map { it.split(" ") }
                .forEach {
                    val cmd = it[0]
                    val offset = it[1].toInt()
                    when (cmd) {
                        FWD -> x += offset
                        UP -> y -= offset
                        DOWN -> y += offset
                    }
                }
        println(x * y)
    }

    override fun solvePart2() {
        var x = 0
        var y = 0
        var aim = 0
        readLines()
                .map { it.split(" ") }
                .forEach {
                    val cmd = it[0]
                    val offset = it[1].toInt()
                    when (cmd) {
                        FWD -> {
                            x += offset
                            y += (aim * offset)
                        }
                        UP -> aim -= offset
                        DOWN -> aim += offset
                    }
                }
        println(x * y)
    }
}

fun main(args: Array<String>) {
    val challenge = Day02()
    challenge.solvePart1()
    challenge.solvePart2()
}