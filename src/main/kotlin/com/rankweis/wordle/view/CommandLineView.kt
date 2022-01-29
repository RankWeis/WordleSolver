package com.rankweis.wordle.view

import com.rankweis.wordle.solver.*
import java.lang.RuntimeException

class CommandLineView {
    fun ask(): WordGuess {
        println("Enter your word (? for partial, * for exact)")

        val str = readLine()!!.lowercase()
        val splitStr = str.split(" ")
        if (splitStr.size != 5)
            throw RuntimeException("Bad input, must be five characters")

        return WordGuess(splitStr.mapIndexed { index, s -> strToLetterGuess(index, s) })
    }

    fun strToLetterGuess(position: Int, str: String): LetterGuess {
        if (str.endsWith("?"))
            return LetterGuess(position, str[0], LetterResult.PartialMatch)
        if (str.endsWith("*"))
            return LetterGuess(position, str[0], LetterResult.ExactMatch)
        return LetterGuess(position, str[0], LetterResult.NoMatch)
    }
    
    fun play(wordList : List<String>) {
        val solver = Solver()
        var history = WordHistory(listOf())
        while(true) {
            history = WordHistory(history.history + ask())
            println(solver.solve(history, wordList))
        }
    }
}