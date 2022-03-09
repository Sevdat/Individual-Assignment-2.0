import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Exam2022_productsKtTest {

    @Test
    fun foo() {
        kotlin.test.assertEquals(
            mutableListOf("яблоки"),
            foo("src/Exam2022 products.txt","00???2 10")
        )
        kotlin.test.assertEquals(
            mutableListOf("яйца, достаточно, 1380 р"),
            foo("src/Exam2022 products.txt","009724 15")
        )
        kotlin.test.assertEquals(
            mutableListOf("яблоки", "молоко"),
            foo("src/Exam2022 products.txt","* 35")
        )
    }
}