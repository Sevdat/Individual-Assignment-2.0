package NewPrograms

import kotlin.math.pow
import kotlin.test.assertEquals

data class Commands(val input:String, val decimal: Int ,val current: Int, val converted: Int)

fun decToBinOrOct(commands: Commands): String{
    val table = truthTable()
    val data = commands.input.split(Regex("""[,.]"""))
    var result = ""
    var count = 0.0
    val biggerToSmaller = commands.current > commands.converted
    val choice = (commands.current == 8 || commands.current == 2 || commands.current == 10) &&
            (commands.converted == 8 || commands.converted == 2 || commands.converted == 10)
    val octToBin = (commands.current == 8 || commands.converted == 2) && (commands.converted == 8 || commands.current == 2)
    for ((i,e) in data.withIndex()){
        var collect = ""
        var empty = e.toInt()

        when{
            choice -> {
                if (biggerToSmaller) {
                 while (empty != 0) {
                        when (i) {
                            0 -> {
                                if (octToBin){
                                    var remains = e.length % 3
                                    while (remains != 0){
                                        collect = "0$e"
                                        remains -= 1
                                    }


                                } else {
                                val remainder = empty % commands.converted
                                collect += remainder.toString()
                                empty = (empty - remainder) / commands.converted
                                }
                            }
                            1 -> {
                                val remainder = "${"0.${empty}".toDouble() * commands.converted}"
                                collect += remainder[0]
                                val amountOfDecimal = (result.length == collect.length - commands.decimal)
                                empty = if (amountOfDecimal) 0 else remainder.drop(2).toInt()
                            }
                        }
                    }
                } else {
                    for ((j,k) in e.withIndex()){
                        val exponent = if (i == 0) (e.length - 1) - j else -j - 1
                        count += k.toString().toDouble()*(commands.current.toDouble().pow(exponent))
                        result = count.toString()
                    }
                }
            }


        }

        result += if (i == 0) collect.reversed() else collect
        result += "."
    }
    return result.dropLast(1)
}

fun truthTable(): MutableList<MutableList<Int>>{
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    var s = 0
    var f = 0
    val list = mutableListOf<MutableList<Int>>()
    for (i in 0..15){
        val k = mutableListOf(a,b,c,d)
        f+=1
        s+=1
        a = if (i in 7..15) 1 else 0
        if (s == 4) b = 1 else if (s == 8) { b = 0; s = 0 }
        if (f == 2) c = 1 else if (f == 4) { c = 0; f = 0 }
        d = if (i % 2 == 0) 1 else 0
        list += k
    }
    return list
}


fun main(){
    fun binOctDecHex(commands: Commands): Any{
        val end = decToBinOrOct(commands)
        println(end)
        return end
    }
    run {
        assertEquals("389.5625",  binOctDecHex(Commands("110000101.1001", 7,2,10)))
        assertEquals("1010010010001.111110111010010111100",  binOctDecHex(Commands("5265,983", 7,10,2)))
        assertEquals("110000101.1001", binOctDecHex(Commands("389,5625", 5,10,2)))

        assertEquals("893.0",  binOctDecHex(Commands("1575", 5,8,10)))
        assertEquals("174.536152375",  binOctDecHex(Commands("124.684", 5,10,8)))
        assertEquals("1575",  binOctDecHex(Commands("893", 5,10,8)))
    }

}


//println(i[stop - count])
