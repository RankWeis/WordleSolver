package com.rankweis.wordle.generator

class WordListGenerator {
    val fileName = "words/words_alpha.txt"
    
    fun getWordList(): List<String> {
        return getFiveLetterWords(getWordsFromResources())
    }
    
    private fun getWordsFromResources(): List<String> {
        return WordListGenerator::class.java.classLoader.getResourceAsStream(fileName).bufferedReader().readLines()
    }
    
    fun getFiveLetterWords(words: List<String>): List<String> {
        return words.filter { it.length == 5 }
    }

    fun getNoRepeatLetters(words: List<String>): List<String> {
        return words.filter { noRepeatLetters(it) }
    }

    private fun noRepeatLetters(word: String): Boolean {
        return word.fold(mapOf<Char, Int>()) { acc, x -> acc.plus(x to (acc[x]?.plus(1) ?: 1)) }
            .filter { it.value > 1 }
            .isEmpty()

    }
}