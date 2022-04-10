import java.io.File

/**
 * Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
 * Выходной файл указывается как -o filename.txt, по умолчанию имя формируется из
 * имени входного файла с добавлением расширения. Алгоритм шифрации XOR. Ключ указывается
 * после -c или -d в шестнадцатеричной системе, длина ключа -- любое целое количество байт.
    Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]
 * Кроме самой программы, следует написать автоматические тесты к ней.
 */

//var text = "Hello World"
//var lol = Integer.toBinaryString('0'.code)
//var key = Integer.toBinaryString('B'.code)
//var solve = Integer.toBinaryString('k'.code)
//01011011
//[
fun encrypt(value:String, key:String):String {

    val newKey2 = mutableListOf<String>()
    for (k in key) {
        var newKey = Integer.toBinaryString(k.code)
        while (newKey.length != 8) {
            if (newKey.length != 8) newKey = "0$newKey"
        }
        newKey2 += mutableListOf(newKey)
    }

    var string = ""
    var fill = ""
    for (e in File(value).readText()) {

        var newValue = Integer.toBinaryString(e.code)
        while (newValue.length != 8) {
            if (newValue.length != 8) newValue = "0$newValue"
        }

            var y = 0
            while (y != newKey2.size) {
                var x = 0
                while (x != 8) {
                    string += if (newValue[x] != newKey2[y][x]) "1" else "0"
                    x += 1
                }
                y += 1
                newValue = string
                string = ""
            }
        string = newValue
        fill += string.toInt(2).toChar().toString()
        string = ""

    }
    println(fill)
    return fill
}
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