package com.ionutciuta.aoc2021.day04

import com.ionutciuta.aoc2021.common.Challenge

class Day04 : Challenge("day04") {
    private val lines: List<String> = readLines().filter { it.isNotEmpty() }
    private val calledNumbers = lines[0].split(",").map { it.toInt() }

    private fun getBoards(): List<Board> {
        val iterator = lines.iterator()

        // skip first line
        iterator.next()

        var boardId = 0;
        val boards = mutableListOf<Board>()
        while (iterator.hasNext()) {
            val boardLines = mutableListOf<String>()
            for (i in 0..4) {
                if (iterator.hasNext())
                    boardLines.add(iterator.next())
            }
            boards.add(Board(boardLines, boardId++))
        }

        return boards
    }

    override fun solvePart1() {
        val boards = getBoards()
        calledNumbers.find { calledNumber ->
            boards.any { board ->
                val isWin = board.callAndMark(calledNumber)
                if (isWin) {
                    println(board.getScore())
                }
                isWin
            }
        }
    }

    override fun solvePart2() {
        val winningBoards = mutableSetOf<String>()
        val boards = getBoards()
        calledNumbers.first { calledNumber ->
            boards.forEach { board ->
                if (winningBoards.size != boards.size) {
                    val isWin = board.callAndMark(calledNumber)
                    if (isWin) {
                        winningBoards.add(board.getId())
                        if (winningBoards.size == boards.size) {
                            println("number $calledNumber, score ${board.getScore()}")
                        }
                    }
                }
            }
            winningBoards.size == boards.size
        }
    }
}

fun main(args: Array<String>) {
    val challenge = Day04()
    challenge.solvePart1()
    challenge.solvePart2()
}

class Board(input: List<String>, id: Int) {
    private val id = "$id"
    private val quickAccessCells = HashMap<Int, Cell>()
    private var lasCalledValue: Int = 0

    fun getId(): String = id

    private val lines: List<List<Cell>> = input.map { rawLine ->
        rawLine
                .split(" ")
                .filter { it.isNotEmpty() }
                .map {
                    val value = it.toInt()
                    val cell = Cell(value)
                    quickAccessCells[value] = cell
                    cell
                }
    }

    private fun isWinning(): Boolean {
        val winningLine = lines.any { line -> line.all { it.called } }
        if (winningLine)
            return true

        for (i in lines.indices) {
            val winningRow = lines.map { line -> line[i] }.all { it.called }
            if (winningRow)
                return true
        }

        return false
    }

    fun callAndMark(calledValue: Int): Boolean {
        lasCalledValue = calledValue
        if (quickAccessCells.containsKey(calledValue)) {
            quickAccessCells[calledValue]!!.called = true
        }
        return isWinning()
    }

    fun getScore(): Int {
        return lasCalledValue * quickAccessCells.values
                .filter { !it.called }
                .map { it.value }
                .sum()
    }
}

data class Cell(val value: Int, var called: Boolean = false)