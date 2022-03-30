import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Exam2022LastCodeKtTest {

    @Test
    fun lastCode() {
        kotlin.test.assertEquals("no",
            lastCode("src/Exam2022LastCode.txt","Petersburg","Nice"))
        kotlin.test.assertEquals("no",
            lastCode("src/Exam2022LastCode.txt","London","Paris"))
        kotlin.test.assertEquals("no",
            lastCode("src/Exam2022LastCode.txt","Frankfurt","Tokyo"))
        kotlin.test.assertEquals("yes",
            lastCode("src/Exam2022LastCode.txt","Petersburg","Frankfurt"))
        kotlin.test.assertEquals("yes",
            lastCode("src/Exam2022LastCode.txt","Frankfurt","London"))


    }

}