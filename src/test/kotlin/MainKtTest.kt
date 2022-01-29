import com.rankweis.wordle.generator.WordListGenerator
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {
    val w = WordListGenerator()
    
    @Test
    fun noRepeatLetters() {
        val noRepeatLetters = w.getNoRepeatLetters(listOf("cat", "hollow", "treasury", "chance", "could"))
        assertTrue(noRepeatLetters.size == 2)
        assertTrue(noRepeatLetters.containsAll(listOf("cat", "could")))
    }
}
