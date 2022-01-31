import java.io.File

fun asf1(textdoc: File):MutableMap<String,Int> {

    val readfile = textdoc.readLines()
    val listSize = textdoc.readLines().size
    val nameList = mutableListOf<String>()
    val stringList = mutableListOf<List<Int>>()
    var d = 0
            while (d != listSize) {
                stringList += readfile[d].replace(Regex("[^0-9]"), " ")
                    .trim().split(" ").filter { p -> p != "" }.map { it.toInt() }
                nameList += readfile[d].replace(Regex("[ 0-9]"), " ")
                    .trim().split(" ")
                when {
                    stringList[d][d] != 0 -> throw IllegalArgumentException("Diagonal 0 Error")
                    stringList[d].size != listSize -> throw IllegalArgumentException("Values not in Square Shape")

                }
                d += 1
                if (d == listSize){
                    when {
                        nameList.size != listSize -> throw IllegalArgumentException("Names not in Square Shape")
                    }
                }
            }
    d = 0
    val teamScore = mutableMapOf<String, Int>()
    val point = mutableListOf<Int>()
            while (d != listSize){
                var f = 0
                while (f != listSize) {
                    if (d != f) {
                        point += when {
                            (stringList[d][f] > stringList[f][d]) -> 3
                            (stringList[d][f] < stringList[f][d]) -> 0
                            else -> 1
                        }
                }
                    f += 1
                }
                teamScore[nameList[d]] = point.sum()
                point.clear()
                d += 1
            }
    println(teamScore)
    return teamScore
}
//fun asf1(textdoc:File):MutableMap<String,Int> {
//
//    val listSize = textdoc.readLines().size
//    var numberList = mutableListOf<String>()
//    var nameList = mutableListOf<String>()
//    val stringList = mutableListOf<List<Int>>()
//    var d = 0
//        for (i in textdoc.readLines()) {
//            numberList = (numberList + i.replace(Regex("[^0-9]"), " ").trim()) as MutableList<String>
//            nameList = (nameList + i.replace(Regex("[ 0-9]"), " ").trim()) as MutableList<String>
//        }
//            while (d != listSize) {
//                stringList += numberList[d].split(" ").filter { p -> p != "" }.map { it.toInt() }
//                when {
//                    stringList[d][d] != 0 -> throw IllegalArgumentException("Diagonal 0 Error")
//                    stringList[d].size != listSize -> throw IllegalArgumentException("Values not in Square Shape")
//                    nameList.size != listSize -> throw IllegalArgumentException("Names not in Square Shape")
//                }
//                d += 1
//            }
//    fun kek(x: Int, y:Int): Pair<Int,Int> {
//        if(x != y) {
//            val normal = stringList[x][y]
//            val reverse = stringList[y][x]
//            return Pair(normal, reverse)
//        }
//        return Pair(-1,-1)
//    }
//  d = 0
//    var sumPoint = mutableListOf<Int>()
//    val teamScore = mutableMapOf<String, Int>()
//    var pair = mutableListOf<Pair<Int,Int>>()
//    while (d != listSize){
//        var f = 0
//        while(f != listSize) {
//            pair = (pair + kek(d,f)) as MutableList<Pair<Int, Int>>
//            f += 1
//        }
//        val point = mutableListOf<Int>()
//        for ((q, w) in pair.filter { i -> i != Pair(-1,-1)}) {
//            when {
//                (q > w) -> point.add(3)
//                (q < w) -> point.add(0)
//                else -> point.add(1)
//            }
//        }
//        sumPoint = (sumPoint + point.sum()) as MutableList<Int>
//        pair.clear()
//        point.clear()
//        teamScore[nameList[d]] = sumPoint[d]
//        d += 1
//    }
//    return teamScore
//}
