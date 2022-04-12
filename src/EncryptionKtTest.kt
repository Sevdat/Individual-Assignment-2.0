import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class EncryptionKtTest {
    @Test
    fun encrypt() {
        File("src/Encrypted.txt").createNewFile()
        kotlin.test.assertEquals("[[[\n[[[",encrypt("src/Encryption.txt","6B"))
        println(File("src/Encrypted.txt").readText())
        println("---")
        kotlin.test.assertEquals("(((\n(((",encrypt("src/Encryption.txt","6B 73"))
        println(File("src/Encrypted.txt").readText())
        File("src/Encrypted.txt").delete()
    }
}
//        kotlin.test.assertEquals("[",encrypt("0","k"))
//        kotlin.test.assertEquals("0",encrypt("[","k"))
//        kotlin.test.assertEquals("k",encrypt("src/Encryption.txt","["))