package com.rankweis.wordle.solver

class Solver {
    
    fun solve(history: WordHistory, wordList: List<String>): List<String> {
        var mutableWordList = wordList
        history.history
            .map { it.letters}
            .flatMap { it }
            .map { mutableWordList = solveAll(history, it, mutableWordList) }
        return mutableWordList
    }
    
    fun solveAll(history: WordHistory, letterGuess: LetterGuess, wordList: List<String>) : List<String> {
        return solveNoMatch(history, letterGuess, solvePartial(letterGuess, solveExact(letterGuess, wordList)))
    }
    
    fun solveExact(letterGuess : LetterGuess, wordList: List<String>): List<String> {
        if(letterGuess.result != LetterResult.ExactMatch) return wordList
        
        return wordList.filter { it[letterGuess.position] == letterGuess.character }
    }
    
    fun solvePartial(letterGuess: LetterGuess, wordList: List<String>) : List<String> {
        if(letterGuess.result != LetterResult.PartialMatch) return wordList
        return wordList.filter { it[letterGuess.position] != letterGuess.character }
            .filter { it.contains(letterGuess.character) }
    }

    fun solveNoMatch(history: WordHistory, letterGuess: LetterGuess, wordList: List<String>) : List<String> {
        if(letterGuess.result != LetterResult.NoMatch) return wordList
        val exactMatches = history.history
            .map { it.letters }
            .flatMap { it }
            .filter { it.result == LetterResult.ExactMatch || it.result == LetterResult.PartialMatch }
        if ( exactMatches.map { it.character }.contains(letterGuess.character)) {
            return solvePartial(letterGuess, wordList)
        }
        return wordList.filter { !it.contains(letterGuess.character) }
    }
}