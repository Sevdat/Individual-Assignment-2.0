import java.io.File

fun asf1(textdoc:File):MutableMap<String,Int> {
    val fileList =  textdoc.readLines()
    println("fileList-----------------= $fileList")

//вынимает все, кроме алфавитов и все, кроме цифр и удаляет сгенерированный пробел
    var numberList = mutableListOf<String>()
    var nameList = mutableListOf<String>()
    for (i in fileList) {
        numberList = (numberList + i.replace(Regex("[^0-9]"), " ").trim()) as MutableList<String>
        nameList = (nameList + i.replace(Regex("[ 0-9]"), " ").trim()) as MutableList<String>
    }
    println("nameList----------------= $nameList")
    println("numberList--------------= $numberList")

    var vs = 0
    var kes = mutableListOf<List<String>>()
    for (i in numberList) {
        while (vs != numberList.size ) {
            kes += numberList[vs].split(" ").filter { p -> p != "" }
            vs += 1
    }
    }
    println("kes---------------------= $kes")

fun kek(x: Int, y:Int): Pair<Int,Int> {
        if(x != y) {
            var list = kes[x][y]
            var list1 = kes[y][x]
            return Pair(list.toInt(), list1.toInt())
        }
    return Pair(-1,-1)
}

//from coordinate to adding score
    var d = -1
    var f = -1
    var list3 = mutableListOf<Int>()
    var list4 = mutableListOf<Int>()
    var point = mutableListOf<Int>()
    val teamScore = mutableMapOf<String, Int>()
    while (d != kes.size - 1){
        d += 1
        var list2 = mutableListOf<Pair<Int,Int>>()
        while(f != kes.size - 1) {
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
        list3 = listOf(point.sum()) as MutableList<Int>
        list4 += list3
        list2.clear()
        point.clear()
        f = -1

        teamScore[nameList[d]] = list4[d]
    }
        println("teamScore---------------= $teamScore")
    return teamScore
}
