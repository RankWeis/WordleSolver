package com.rankweis.wordle.solver

import java.util.stream.IntStream

class NextGuessHelper {
    fun bestNextGuess(history: WordHistory, wordList: List<String>): List<String> {
        val omittedLetters = history.history
            .map { it.letters }
            .flatMap { it }
            .map { it.character }

        val sortedHistogram = wordList.fold(mapOf<Char, Int>()) { acc, x -> addToHistogram(acc, x) }
            .filter { !omittedLetters.contains(it.key) }
            .toList()
            .sortedBy { (_, v) -> v }
            .map { it.first }
            .reversed()
        return findBestMatch(sortedHistogram, wordList)

    }

    fun addToHistogram(histogram: Map<Char, Int>, str: String): Map<Char, Int> {
        return str.fold(histogram) { acc, x -> acc.plus(x to (acc[x]?.plus(1) ?: 1)) }
    }

    fun findBestMatch(histogram: List<Char>, wordList: List<String>): List<String> {
        var previousValue = wordList
        histogram.map { w ->
            val updatedWords = previousValue.filter { it.contains(w) }
            if(updatedWords.size > 1) {
                previousValue = updatedWords
            }
        }
        return previousValue
    }

}