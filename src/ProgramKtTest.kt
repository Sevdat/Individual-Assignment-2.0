import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ProgramKtTest {
    @Test
    fun asf4x4() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 4, "Бавария" to 1, "Интер" to 3, "Барселона" to 7),
            asf(File("src/text.txt"))
        )
    }

    @Test
    fun asf11x11() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 11, "Бавария" to 4, "Интер" to 9, "Барселона" to 19,
            "lol" to 11, "lollord" to 4, "lolking" to 9, "lolgod" to 19, "kek" to 11, "keklord" to 4, "kekking" to 9),
            asf(File("src/text1.txt"))
        )
    }
    @Test
//make an if statement separating one while statement for 4 or less and another for values bigger than 4
    fun asf3x3() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 4, "Бавария" to 1, "Интер" to 2),
            asf(File("src/text2.txt"))
        )
    }
}

