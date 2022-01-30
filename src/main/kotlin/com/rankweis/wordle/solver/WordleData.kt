package com.rankweis.wordle.solver

enum class LetterResult {
    ExactMatch,
    PartialMatch,
    NoMatch
}

data class LetterGuess(val position: Int, val character: Char, val result: LetterResult)

data class WordGuess(val letters: List<LetterGuess>)

data class WordHistory (val history: List<WordGuess>)
