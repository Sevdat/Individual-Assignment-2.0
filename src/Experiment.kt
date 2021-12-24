import java.io.File

fun asf1(textdoc:File):MutableMap<String,Int> {

//вынимает все, кроме алфавитов и все, кроме цифр и удаляет сгенерированный пробел

//using the line size,
    var d = -1
    var list4 = mutableListOf<Int>()
    val teamScore = mutableMapOf<String, Int>()

    while (d != textdoc.readLines().size - 1){

        var numberList = mutableListOf<String>()
        var nameList = mutableListOf<String>()
        var kes1 = mutableListOf<List<String>>()
        var vs = 0
        for (i in textdoc.readLines()) {
            numberList = (numberList + i.replace(Regex("[^0-9]"), " ").trim()) as MutableList<String>
            nameList = (nameList + i.replace(Regex("[ 0-9]"), " ").trim()) as MutableList<String>
            while (vs != numberList.size) {
                kes1 += numberList[vs].split(" ").filter { p -> p != "" }
                vs += 1
            }
        }
        println("nameList----------------= $nameList")
        println("numberList--------------= $numberList")
        println("kes1--------------------= $kes1")

        fun kek(x: Int, y:Int): Pair<Int,Int> {
            if(x != y) {
                var list = kes1[x][y]
                var list1 = kes1[y][x]
                return Pair(list.toInt(), list1.toInt())
            }
            return Pair(-1,-1)
        }

        d += 1

        var list2 = mutableListOf<Pair<Int,Int>>()
        var f = -1
        while(f != textdoc.readLines().size - 1) {
            f += 1
            list2 = (list2 + kek(d,f)) as MutableList<Pair<Int, Int>>
        }

        var point = mutableListOf<Int>()
        for ((q, w) in list2.filter { i -> i != Pair(-1,-1)}) {
            when {
                (q > w) -> point.add(3)
                (q < w) -> point.add(0)
                else -> point.add(1)
            }
        }

       var list3 = listOf(point.sum()) as MutableList<Int>
        list4 += list3
        list2.clear()
        point.clear()

        teamScore[nameList[d]] = list4[d]
    }
    println("teamScore---------------= $teamScore")
    return teamScore
}
