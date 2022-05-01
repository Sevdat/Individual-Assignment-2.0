import java.io.File
import kotlin.experimental.xor

/**
 * Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
 * Выходной файл указывается как -o filename.txt, по умолчанию имя формируется из
 * имени входного файла с добавлением расширения. Алгоритм шифрации XOR. Ключ указывается
 * после -c или -d в шестнадцатеричной системе, длина ключа -- любое целое количество байт.
    Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]
 * Кроме самой программы, следует написать автоматические тесты к ней.
 */
//works slower
//newKey = "00000000".dropLast(newKey.length) + newKey

// string needs to be file
fun encrypt(text: String, key:String) {

    val removeSpace = key.replace(" ","")
    if (
        key.replace(Regex("""[0-9A-F ]"""), "").isNotEmpty() || removeSpace.length % 2 != 0
    )
        throw IllegalArgumentException("Key Error")

    val splitKey = removeSpace.split("").filter { e-> e != "" }
    val binaryKey = mutableListOf<Byte>()
    var doubleChar = 0
    while (doubleChar <= splitKey.size / 2) {
        val hex = "${splitKey[doubleChar]}${splitKey[doubleChar + 1]}"
        val newKey = hex.toInt(16).toByte()
        binaryKey += newKey
        doubleChar += 2
    }
//use
    var xor: Byte
    val edit = File("src/Encrypted.txt").outputStream()
    for (e in File(text).inputStream().readBytes()) {
        var y = 0
        var s = e
        when {
            (e != '\n'.code.toByte() && e != '\r'.code.toByte()) -> {
                while (y != binaryKey.size) {
                    xor = s xor binaryKey[y]
                    s = xor
                    y += 1
                    if (y == binaryKey.size) edit.write(xor.toInt())
                }
            }
            (e == '\r'.code.toByte()) -> edit.write('\r'.code)
        }
    }
    edit.close()
}
// 13 = 'r' 10 = 'n'
//InputStream()
//Reader()
//Writer()
//  val read = File(text).reader().read()

//fun encrypt(text:String, key:String):String {
//
//    val splitKey = key.replace(" ","").split("").filter { e-> e != "" }
//    val binaryKey = mutableListOf<String>()
//    var doubleChar = 0
//    while (doubleChar <= splitKey.size / 2) {
//        val hex = "${splitKey[doubleChar]}${splitKey[doubleChar + 1]}"
//        var newKey = Integer.toBinaryString(hex.toInt(16))
//        while (newKey.length != 8) {
//            newKey = "0$newKey"
//        }
//        binaryKey += newKey
//        doubleChar += 2
//    }
//
//    var xor = ""
//    var convert = ""
//    for (e in File(text).readText()) {
//        when {
//            (e != '\n' && e != '\r') -> {
//                var binaryChar = Integer.toBinaryString(e.code)
//                while (binaryChar.length != 8) {
//                    binaryChar = "0$binaryChar"
//                }
//
//                var y = 0
//                while (y != binaryKey.size) {
//                    var x = 0
//                    while (x != 8) {
//                        xor += if (binaryChar[x] != binaryKey[y][x]) "1" else "0"
//                        x += 1
//                    }
//                    y += 1
//                    binaryChar = xor
//                    xor = ""
//                }
//                xor = binaryChar
//                convert += xor.toInt(2).toChar().toString()
//                xor = ""
//            }
//            (e == '\r') -> convert += "\n"
//        }
//    }
//    File("src/Encrypted.txt").writeText(convert)
//    return convert
//}

//var text = "Hello World"
//var lol = Integer.toBinaryString('0'.code)
//var key = Integer.toBinaryString('B'.code)
//var solve = Integer.toBinaryString('k'.code)
//01011011
//[
//      var newKey = Integer.toBinaryString(k.code)
//val splitKey = "6B73".split(Regex("""(|..)"""))
//println(Integer.toBinaryString("08".toInt(16)))
// AZ = 33-58 , 33 = A
//    println("FF".toInt(16))
//    println('a'.code)
//    println(255 - 97)
//    println("FF".toInt(16) xor 'a'.code)
//    println("FF".toInt(16) xor 'a'.code xor "FF".toInt(16))

//lines (22 sloc) 682 Bytes  ???????
//import java.util.function.BinaryOperator
//
//fun main() {
//    var text = "Hello World"
//    var lol = Integer.toBinaryString('0'.toInt())
//    var key = Integer.toBinaryString('B'.toInt())
//    var solve = Integer.toBinaryString('k'.toInt())
//
//    while (lol.length != 8 || key.length != 8 || solve.length != 8) {
//        if (lol.length != 8) lol = "0$lol"
//        if (key.length != 8) key = "0$key"
//        if (solve.length != 8) solve = "0$solve"
//    }
//
//    var x = 0
//    var string = ""
//    for (i in lol) {
//        string += if (i != solve[x]) "1" else "0"
//
//        x+=1
//    }
//
//    var converter = string.toInt(2).toChar()
//    println(string)
//    println(converter)
//
//}