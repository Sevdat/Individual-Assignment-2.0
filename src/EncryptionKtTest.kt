import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EncryptionKtTest {

    @Test
    fun encrypt() {
//        kotlin.test.assertEquals("[",encrypt("0","k"))
//        kotlin.test.assertEquals("0",encrypt("[","k"))
        kotlin.test.assertEquals("[[[",encrypt("src/Encryption.txt","k"))
//        kotlin.test.assertEquals("k",encrypt("src/Encryption.txt","["))
        kotlin.test.assertEquals("(((",encrypt("src/Encryption.txt","ks"))

    }
}