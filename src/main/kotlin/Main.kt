import com.rankweis.wordle.generator.WordListGenerator
import com.rankweis.wordle.view.CommandLineView

fun main(args: Array<String>) {
    val wordListGenerator = WordListGenerator()
    val commandLinePlayer = CommandLineView()
    commandLinePlayer.play(wordListGenerator.getWordList())
}

