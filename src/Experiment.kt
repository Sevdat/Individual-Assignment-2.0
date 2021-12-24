import java.io.File

fun asf1(textdoc:File):MutableMap<String,Int> {

    var numberList = mutableListOf<String>()
    var nameList = mutableListOf<String>()
    var stringList = mutableListOf<List<String>>()
    var d = -1
    val teamScore = mutableMapOf<String, Int>()
    val listSize = textdoc.readLines().size
    var list4 = mutableListOf<Int>()
    var list2 = mutableListOf<Pair<Int,Int>>()
    var point = mutableListOf<Int>()
    var sd = 0
    var vs = 0

    while (d != listSize - 1){

        if (sd == 0){//вынимает все, кроме алфавитов и все, кроме цифр и удаляет сгенерированный пробел
            for (i in textdoc.readLines()) {

                numberList = (numberList + i.replace(Regex("[^0-9]"), " ").trim()) as MutableList<String>
                nameList = (nameList + i.replace(Regex("[ 0-9]"), " ").trim()) as MutableList<String>
                while (vs != numberList.size) {
                    stringList += numberList[vs].split(" ").filter { p -> p != "" }
                    vs += 1
                }
            }
            sd += 1
        }

        fun kek(x: Int, y:Int): Pair<Int,Int> {
            if(x != y) {
                var list = stringList[x][y]
                var list1 = stringList[y][x]
                return Pair(list.toInt(), list1.toInt())
            }
            return Pair(-1,-1)
        }

        d += 1
        var f = -1
        while(f != listSize - 1) {
            f += 1
            list2 = (list2 + kek(d,f)) as MutableList<Pair<Int, Int>>
        }
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
    println("nameList----------------= $nameList")
    println("numberList--------------= $numberList")
    println("kes1--------------------= $stringList")
    println("teamScore---------------= $teamScore")
    return teamScore
}
