package NewPrograms

import kotlin.math.log2
import kotlin.math.pow
import kotlin.test.assertEquals

data class Commands(val input: String, val decimal: Int, val current: Int, val converted: Int)


fun decToBinOrOct(commands: Commands): String {
    val data = commands.input.split(Regex("""[,.]"""))
    var result = ""
    var count = 0.0
    val biggerToSmaller = commands.current > commands.converted
    val binOct = (commands.current == 2 && commands.converted == 8) || (commands.current == 8 && commands.converted == 2)
    val binDec = (commands.current == 2 && commands.converted == 10) || (commands.current == 10 && commands.converted == 2)
    val binHex = (commands.current == 2 && commands.converted == 16) || (commands.current == 16 && commands.converted == 2)
    val octDec = (commands.current == 8 && commands.converted == 10) || (commands.current == 10 && commands.converted == 8)
    val octHex = (commands.current == 8 && commands.converted == 16) || (commands.current == 16 && commands.converted == 8)
    val decHex = (commands.current == 10 && commands.converted == 16) || (commands.current == 16 && commands.converted == 10)
    val binDecOrOctDec = binDec || binOct || octDec
    for ((i, e) in data.withIndex()) {
        var collect = ""
        when {
            binOct -> {
                if (biggerToSmaller) {
                    for (k in e) {
                        var binary = truth(k.toString().toInt())
                        while (binary.length != 3) {
                            binary = "0$binary"
                        }
                        result += binary
                    }
                } else {
                    collect = e
                    var remains = e.length % 3
                    while (remains != 0){
                        remains -= 1
                        if (remains != 0) collect = if (i == 0) "0$collect" else "${collect}0"
                    }
                    var stage = 3
                    var addition = 0
                    for (s in collect){
                        stage -= 1
                        addition += s.toString().toInt()*((2.0).pow(stage).toInt())
                        if (stage == 0) {
                            stage = 3
                            result += addition.toString()
                            addition = 0
                        }
                    }
                    if (i == 0 && data.size != 1) result += "."
                }
            }
            binDecOrOctDec -> {
                if (biggerToSmaller) {
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
                    if (data.size == 1) result = result.dropLast(1)
                } else {
                    for ((j, k) in e.withIndex()) {
                        val exponent = if (i == 0) (e.length - 1) - j else -j - 1
                        count += k.toString().toDouble() * (commands.current.toDouble().pow(exponent))
                        result = count.toString()
                    }
                }
            }
        }
    }
    return result
}

fun truth(value: Int): String {
    var binary = ""
    if (value != 0) {
        val log = log2(value.toDouble())
        val amount = log.toInt() + 1
        for (i in 0 until amount) {
            binary += value / ((2.0).pow(i).toInt()) % 2
        }
    } else binary = "0"
    return binary

}


fun main() {
    fun binOctDecHex(commands: Commands): Any {
        val end = decToBinOrOct(commands)
        return end
    }
    run {
        assertEquals("389.5625", binOctDecHex(Commands("110000101.1001", 7, 2, 10)))
        assertEquals("1010010010001.111110111010010111100", binOctDecHex(Commands("5265,983", 7, 10, 2)))
        assertEquals("110000101.1001", binOctDecHex(Commands("389,5625", 5, 10, 2)))

        assertEquals("893.0", binOctDecHex(Commands("1575", 5, 8, 10)))
        assertEquals("174.536152375", binOctDecHex(Commands("124.684", 5, 10, 8)))
        assertEquals("1575", binOctDecHex(Commands("893", 5, 10, 8)))

        assertEquals("001101111101", binOctDecHex(Commands("1575", 5, 8, 2)))
        assertEquals("1575", binOctDecHex(Commands("001101111101", 5, 2, 8)))
        assertEquals("250.0765066", binOctDecHex(Commands("10101000.00011111010100011011", 5, 2, 8)))
    }

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