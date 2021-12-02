package com.ionutciuta.aoc2021.common

import java.io.File

abstract class Challenge(val input: String) {
    private val inputFile = File("${File("./").canonicalPath}/input/$input")

    fun readLines(): List<String> = inputFile.readLines()

    fun readLinesAsInts(): List<Int> = readLines().map { it.toInt() }.toList()

    abstract fun solvePart1();

    abstract fun solvePart2();
}