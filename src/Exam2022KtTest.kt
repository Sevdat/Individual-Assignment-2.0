import org.junit.jupiter.api.Test

import java.io.File

internal class Exam2022KtTest {

    @Test
    fun myFun() {

        kotlin.test.assertEquals("Апрель 8 - 48",
            myFun("src/Exam2022.txt", "Март 22...Май 8")
                )
        kotlin.test.assertEquals("Апрель 9 - 42",
            myFun("src/Exam2022.txt", "Апрель 9...15")
        )


    }
}

// @Test
//    fun myFun() {
//        val s = File("src/Exam2022.txt")
//        var x = 0
//            while (x != File("src/Exam2022.txt").readLines().size) {
//                kotlin.test.assertEquals(
//                    0,
//                    myFun(
//                        "src/Exam2022.txt",
//                        s.readLines()[x].split(" ").drop(1).joinToString { it }
//                    )
//
//                )
//                x += 1
//            }
//
//
//    }