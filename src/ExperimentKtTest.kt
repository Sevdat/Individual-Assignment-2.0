import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ExperimentKtTest {
    @Test
    fun asf4x4() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 4, "Бавария" to 1, "Интер" to 3, "Барселона" to 7),
            asf1(File("src/text.txt"))
        )
    }

    @Test
    fun asf11x11() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 14, "Бавария" to 5, "Интер" to 10, "Барселона" to 22, "lol" to 14,
            "lollord" to 5, "lolking" to 10, "lolgod" to 22, "kek" to 14, "keklord" to 5, "kekking" to 10),
            asf1(File("src/text1.txt"))
        )
    }
    @Test
//make an if statement separating one while statement for 4 or less and another for values bigger than 4
    fun asf3x3() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 4, "Бавария" to 1, "Интер" to 2),
            asf1(File("src/text2.txt"))
        )
    }
    @Test
//make an if statement separating one while statement for 4 or less and another for values bigger than 4
    fun asf2dignum() {
        kotlin.test.assertEquals(mapOf("Арсенал" to 4, "Бавария" to 1, "Интер" to 3, "Барселона" to 7),
            asf1(File("src/text3.txt"))
        )
    }
}