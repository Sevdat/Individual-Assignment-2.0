import java.io.File

fun asf(textdoc:File):MutableMap<String,Int> {
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
    var kes = mutableListOf<String>()
    for (i in numberList) {
        while (vs != numberList.size ) {
            kes += numberList[vs].split(" ").filter { p -> p != "" }
            vs += 1
    }
    }
    println(kes)

//удаляет квадратную скобку и разделяет пробелы запятой, от list<String> к list<Int>
    var intList = mutableListOf<Int>()
    for (i in kes) {
        intList += i.toInt()
    }
    println("intList-----------------= $intList")
//для цикла из координаты (why does orginize.clear() not work but like this it does)
    var n = 1
    var u = 0
    var orglist = mutableListOf<List<Int>>()
    while (intList.size - u != 0) {
        var ds = mutableListOf<Int>()
        while (intList.size / nameList.size * n - u != 0) {
            ds = (ds + listOf(intList[u])) as MutableList<Int>
            u += 1
        }
        orglist = (orglist + listOf(ds)) as MutableList
        n += 1
    }
    println("orglist----------------=$orglist")

fun kek(x: Int, y:Int): Pair<Int,Int> {
        if(x != y) {
            var list = orglist[x][y]
            var list1 = orglist[y][x]
            return Pair(list, list1)
        }
    return Pair(-1,-1)
}

//from coordinate to adding score
    var d = -1
    var f = -1
    var list3 = mutableListOf<Int>()
    val point = mutableListOf<Int>()
    while (d != orglist.size - 1){
        d += 1
        var list2 = mutableListOf<Pair<Int,Int>>()
        while(f != orglist.size - 1) {
            f += 1
            list2 = (list2 + kek(d,f)) as MutableList<Pair<Int, Int>>
        }
        for ((q, w) in list2.filter { i -> i != Pair(-1,-1)}) {
            when {
                (q.toString().toInt() > w.toString().toInt()) -> point.add(3)
                (q.toString().toInt() < w.toString().toInt()) -> point.add(0)
                else -> point.add(1)
            }
        }
        list3 = (list3 + point) as MutableList<Int>
        list2.clear()
        f = -1
    }

    //combining the score
    var score = mutableListOf<Int>()
    var combineDelete = mutableListOf<Int>()
    var g = 0
    var j = 0
    while(g != orglist.size) {
        g += 1
        while (j != (orglist.size - 1) * g) {
            combineDelete = (combineDelete + point[j]) as MutableList
            j += 1
        }
        score = (score + combineDelete.sum()) as MutableList<Int>
        println("combineDelete-----------= $combineDelete")
        combineDelete.clear()
    }

        val teamScore = mutableMapOf<String, Int>()
        var r = -1
        while (r != orglist.size - 1) {
            r += 1
            teamScore[nameList[r]] = score[r]

        }
        println("teamScore---------------= $teamScore")
    return teamScore
}
