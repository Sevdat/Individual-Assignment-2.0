import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Gluhih_is_very_zloyKtTest {

    @Test
    fun lol() {
        kotlin.test.assertEquals("ef ", lol(1))
        kotlin.test.assertEquals("ef ef ", lol(11))
        kotlin.test.assertEquals("ef abged abgcd fgbc afgcd fgedc fabc abcdefg abfgc abcdef ",lol(1234567890))
        kotlin.test.assertEquals("abfgc abcdefg fabc fgedc afgcd fgbc abgcd abged ef ",lol(987654321))
    }
}