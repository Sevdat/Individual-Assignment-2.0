package NewPrograms

import kotlin.math.log2
import kotlin.math.pow
import kotlin.test.assertEquals

///I don't like it...I want to write it in 1 loop :/

var result = ""
var collect = ""
var count = 0.0

data class Commands(val input: String, val decimal: Int, val current: Int, val converted: Int) {
    val binOct = (current == 2 && converted == 8)
    val octBin = (current == 8 && converted == 2)
    val binDec = (current == 2 && converted == 10)
    val decBin = (current == 10 && converted == 2)

    val binHex = (current == 2 && converted == 16) //
    val hexBin = (current == 16 && converted == 2) //

    val octDec = (current == 8 && converted == 10)
    val decOct = (current == 10 && converted == 8)

    val octHex = (current == 8 && converted == 16) //
    val hexOct = (current == 16 && converted == 8)
    val decHex = (current == 10 && converted == 16)
    val hexDec = (current == 16 && converted == 10) //

    val binDecBinOctOctDec = binDec || binOct || octDec
    val decBinOctBinDecOct = decBin || octBin || decOct
}

fun binOctDecHex(commands: Commands): String {
    result = ""
    val data = commands.input.split(Regex("""[,.]"""))
    val size = data.size
    for ((i, e) in data.withIndex()) {
        collect = ""
        when {
            commands.octBin -> octToBinary(i,e,commands)
            commands.binOct -> numberConvert(i, e, size)
            commands.binDecBinOctOctDec -> binaryToOctOrDec(i, e, commands)
            commands.decBinOctBinDecOct -> decBinOct(i, e, size, commands)
            commands.hexBin -> octToBinary(i,e,commands)
        }
    }
    collect = ""
    return result
}

fun truthTable(value: Int): String {
    var binary = ""
    if (value != 0) {
        val log = log2(value.toDouble())
        val amount = log.toInt() + 1
        for (i in 0 until amount) {
            binary += value / ((2.0).pow(i).toInt()) % 2
        }
    } else binary = "0"
    return binary.reversed()
}
fun finder(s:Char):String{
    var equivelant = ""
    for (i in 0..5) {
        if (s == 'A' + i) equivelant = truthTable(i + 10)}
    println(equivelant)
    return equivelant
}

fun octToBinary(i: Int,e: String, commands: Commands) {
    val binLength = if (commands.current == 16) 4 else 3
    if (i == 1) result += "."
    for (k in e) {
        var binary = if (commands.current == 16 && k !in '0'..'9') finder(k) else truthTable(k.toString().toInt())
        while (binary.length != binLength) {
            binary = if (i == 0) "0$binary" else "${binary}0"
        }
        result += binary
    }

}

fun numberConvert(i: Int, e: String, size: Int) {
    collect = e
    var remains = e.length % 3
    while (remains != 0) {
        remains -= 1
        if (remains != 0) collect = if (i == 0) "0$collect" else "${collect}0"
    }
    var stage = 3
    var addition = 0
    for (s in collect) {
        stage -= 1
        addition += s.toString().toInt() * ((2.0).pow(stage).toInt())
        if (stage == 0) {
            stage = 3
            result += addition.toString()
            addition = 0
        }
    }
    if (i == 0 && size != 1) result += "."
}

fun decBinOct(i: Int, e: String, size: Int, commands: Commands) {
    var empty = e.toInt()
    while (empty != 0) {
        if (i == 0) {
            val remainder = empty % commands.converted
            collect += remainder.toString()
            empty = (empty - remainder) / commands.converted
        } else {
            val remainder = "${"0.${empty}".toDouble() * commands.converted}"
            collect += remainder[0]
            val amountOfDecimal = (result.length == collect.length - commands.decimal)
            empty = if (amountOfDecimal) 0 else remainder.drop(2).toInt()
        }
    }
    result += if (i == 0) "${collect.reversed()}." else collect
    if (size == 1) result = result.dropLast(1)
}

fun binaryToOctOrDec(i: Int, e: String, commands: Commands) {
    for ((j, k) in e.withIndex()) {
        val exponent = if (i == 0) (e.length - 1) - j else -j - 1
        count += k.toString().toDouble() * (commands.current.toDouble().pow(exponent))
    }
    result = if (result == "") count.toInt().toString() else (result.toDouble() + count).toString()
    count = 0.0
}


fun binOctDecHexTest() {
    assertEquals("389.5625", binOctDecHex(Commands("110000101.1001", 7, 2, 10)))
    assertEquals("1010010010001.111110111010010111100", binOctDecHex(Commands("5265,983", 7, 10, 2)))
    assertEquals("110000101.1001", binOctDecHex(Commands("389,5625", 5, 10, 2)))

    assertEquals("893", binOctDecHex(Commands("1575", 5, 8, 10)))
    assertEquals("174.536152375", binOctDecHex(Commands("124.684", 5, 10, 8)))
    assertEquals("1575", binOctDecHex(Commands("893", 5, 10, 8)))

    assertEquals("001101111101", binOctDecHex(Commands("1575", 5, 8, 2)))
    assertEquals("1575", binOctDecHex(Commands("001101111101", 5, 2, 8)))

    assertEquals("0111101010011100.10001110", binOctDecHex(Commands("7A9C.8E", 5, 16, 2)))
}

fun main() {
    binOctDecHexTest()
}


//println(i[stop - count])
//fun truthTable(): MutableList<MutableList<Int>>{
//    var a = 0
//    var b = 0
//    var c = 0
//    var d = 0
//    var s = 0
//    var f = 0
//    val list = mutableListOf<MutableList<Int>>()
//    for (i in 0..15){
//        val k = mutableListOf(a,b,c,d)
//        f+=1
//        s+=1
//        a = if (i in 7..15) 1 else 0
//        if (s == 4) b = 1 else if (s == 8) { b = 0; s = 0 }
//        if (f == 2) c = 1 else if (f == 4) { c = 0; f = 0 }
//        d = if (i % 2 == 0) 1 else 0
//        list += k
//    }
//    return list
//}
//
//for binToOct
//println("llol")
//collect = e
//var remains = e.length % 3
//println(remains)
//while (remains != -1){
//    collect = if (i == 0) "0$collect" else "${collect}0"
//    remains -= 1
//}
//empty = 0
//println(collect)