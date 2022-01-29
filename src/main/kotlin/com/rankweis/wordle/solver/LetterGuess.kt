package com.rankweis.wordle.solver


data class LetterGuess(val position: Int, val character: Char, val result: LetterResult)