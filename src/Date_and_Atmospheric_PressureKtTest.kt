import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Date_and_Atmospheric_PressureKtTest {

    @Test
    fun foo() {
        kotlin.test.assertEquals(
        mutableListOf("24.01: 763", "30.01: 756", "2.02: 752", "5.02: 745", "10.02: 739"),
        foo(
            File("src/Date and Atmo.txt").readLines().joinToString(),
            "e < prev",
            " "
        )
    )
        kotlin.test.assertEquals(
            mutableListOf("5.02: 745", "10.02: 739", "14.02: 742"),
            foo(
                File("src/Date and Atmo.txt").readLines().joinToString(),
                "x < 745",
                " "
            )
        )
        kotlin.test.assertEquals(
            mutableListOf("5.02: 745", "14.02: 742"),
            foo(
                File("src/Date and Atmo.txt").readLines().joinToString(),
                "740 > x < 750",
                " "
            )
        )

//        foo(
//     "01.01: 755, " +
//         "05.01: 758, " +
//         "12.01: 765, " +
//         "20.01: 768, " +
//         "24.01: 763, " +
//         "30.01: 756, " +
//         "2.02: 752, " +
//         "5.02: 745, " +
//         "10.02: 739, " +
//         "14.02: 742",
//            "e < 768", "t"))
    }
}