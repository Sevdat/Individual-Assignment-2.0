import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import java.io.File

internal class Date_and_Atmospheric_PressureKtTest {

    @Test
    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    @Tag("Example")
    fun foo() {
        foo("src/Date and Atmo.txt", "e < prev", "src/temp.txt")
        assertFileContent(
            "src/temp.txt",
            """24.01: 763, 30.01: 756, 2.02: 752, 5.02: 745, 10.02: 739"""
        )
        File("src/temp.txt").delete()

        foo("src/Date and Atmo.txt", "x < 745", "src/temp.txt")
        assertFileContent(
            "src/temp.txt",
            """5.02: 745, 10.02: 739, 14.02: 742"""
        )
        File("src/temp.txt").delete()

        foo("src/Date and Atmo.txt", "740 > x < 750", "src/temp.txt")
        assertFileContent(
            "src/temp.txt",
            """5.02: 745, 14.02: 742"""
        )
        File("src/temp.txt").delete()
    }
}

//        kotlin.test.assertEquals(
//        mutableListOf("24.01: 763", "30.01: 756", "2.02: 752", "5.02: 745", "10.02: 739"),
//        foo(
//            File("src/Date and Atmo.txt").readLines().joinToString(),
//            "e < prev",
//            " "
//        )
//    )
//        kotlin.test.assertEquals(
//            mutableListOf("5.02: 745", "10.02: 739", "14.02: 742"),
//            foo(
//                File("src/Date and Atmo.txt").readLines().joinToString(),
//                "x < 745",
//                " "
//            )
//        )
//        kotlin.test.assertEquals(
//            mutableListOf("5.02: 745", "14.02: 742"),
//            foo(
//                File("src/Date and Atmo.txt").readLines().joinToString(),
//                "740 > x < 750",
//                " "
//            )
//        )