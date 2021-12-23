import java.io.File
fun asf1(textdoc:File):MutableMap<String,Int> {

//из текстового файла в список. Считывает каждую строку и преобразует каждую строку в индекс списка
    val fileList = textdoc.readLines()
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

//удалите лишние пробелы(https://www.youtube.com/watch?v=BSr6cLoyHek&t=61s)
    var intString = ""
    var checker = ""
    for (i in numberList.toString()) {
        if (!(checker == " " && i == ' ')) {
            intString = (intString + i)
        }
        checker = i.toString()
    }
    println("intString---------------= $intString")

//удаляет квадратную скобку и разделяет пробелы запятой
    var stringIntList = mutableListOf<String>(intString)
    var splitString = mutableListOf<String>()
    for (i in stringIntList) {
        splitString = (splitString + i.replace(Regex("[\\[\\]']|,"), "").split(" ")) as MutableList<String>
    }
    println("stringIntList-----------= $stringIntList")
    println("splitString-------------= $splitString")


//от list<String> к list<Int>
    var intList = mutableListOf<Int>()
    for (i in splitString) {
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
    println("orglist-----------------= $orglist")
//дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
data class cell(val m:Int,val l: Int)
    var x = 0
    var normalCoordinate = mutableMapOf<cell, Int>()
    var reverseCoordinate = mutableMapOf<cell, Int>()
    while (x != orglist.size) {
        val giveCoordinate = mutableMapOf<cell, Int>()
        val reverseGiveCoordinate = mutableMapOf<cell, Int>()
        var y = -1
        while (y != orglist.size - 1) {
            y += 1
            if (x != y) {
                giveCoordinate[cell(y, x)] = orglist[y][x]
                reverseGiveCoordinate[cell(x, y)] = orglist[y][x]
                normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<cell, Int>
                reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<cell, Int>
            }
        }
        x += 1
    }
    println("normalCoordinate--------= $normalCoordinate")
    println("reverseCoordinate-------= $reverseCoordinate")

//Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
    var pair = mutableListOf<Pair<Int, Int>>()
    for ((i, _) in reverseCoordinate) {
        val joinCoordinate = listOf(Pair(normalCoordinate[i], reverseCoordinate[i]))
        pair = (pair + joinCoordinate) as MutableList<Pair<Int, Int>>
    }

    println("pair--------------------= $pair")

//от каждой пары начисляются очки в зависимости от первого номера
    val point = mutableListOf<Int>()
    for ((d, s) in pair) {
        when {
            (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
            (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
            else -> point.add(1)
        }
    }
    println("point-------------------= $point")

//добавляет значения вместе в зависимости от размера списка
    var score = mutableListOf<Int>()
    var combineDelete = mutableListOf<Int>()
    var g = 0
    var f = 0
    while(g != numberList.size) {
        g += 1
        while (f != (numberList.size -1) * g) {
            combineDelete = (combineDelete + point[f]) as MutableList
            f += 1
        }
        score = (score + combineDelete.sum()) as MutableList<Int>
        println("combineDelete-----------= $combineDelete")
        combineDelete.clear()
    }
    println("score-------------------= $score")

//объединяет имена и добавленные баллы вместе
    val teamScore = mutableMapOf<String, Int>()
    var r = -1
    while (r != numberList.size - 1) {
        r += 1
        teamScore[nameList[r]] = score[r]

    }
    println("teamScore---------------= $teamScore")
    return teamScore
}
////Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//    val filterCoordinate = normalCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()
//
//    val filterReverseCoordinate = reverseCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()
//    println("filterCoordinate--------= $filterCoordinate")
//    println("filterReverseCoordinate-= $filterReverseCoordinate")
//
////Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//    var pair = mutableListOf<Pair<Int, Int>>()
//    for ((i, _) in reverseCoordinate) {
//        val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i])).filter { (i, e) -> i != null && e != null }
//        pair = (pair + joinCoordinate) as MutableList<Pair<Int, Int>>
//    }
//    println("pair--------------------= $pair")
//
////отфильтровывает null значения
//    val nullFilter = pair.filter { (i, e) -> i != null && e != null }
//    println("nullFilter--------------= $nullFilter")
//
////от каждой пары начисляются очки в зависимости от первого номера
//    val point = mutableListOf<Int>()
//    for ((d, s) in nullFilter) {
//        when {
//            (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//            (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//            else -> point.add(1)
//        }
//    }
//    println("point-------------------= $point")
//
////добавляет значения вместе в зависимости от размера списка
//    var score = mutableListOf<Int>()
//    var combineDelete = mutableListOf<Int>()
//    var g = 0
//    var f = 0
//    while(g != numberList.size) {
//        g += 1
//        while (f != (numberList.size -1) * g) {
//            combineDelete = (combineDelete + point[f]) as MutableList
//            f += 1
//        }
//        score = (score + combineDelete.sum()) as MutableList<Int>
//        println("combineDelete-----------= $combineDelete")
//        combineDelete.clear()
//    }
//    println("score-------------------= $score")
//
////объединяет имена и добавленные баллы вместе
//    val teamScore = mutableMapOf<String, Int>()
//    var r = -1
//    while (r != numberList.size - 1) {
//        r += 1
//        teamScore[nameList[r]] = score[r]
//
//    }
//    println("teamScore---------------= $teamScore")
//}




//    println("numberList--------------= $numberList")
//    println(numberList[0][3])
//    println("nameList----------------= $nameList")
////дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//    var normalCoordinate = mutableMapOf<Double, Char>()
//    var reverseCoordinate = mutableMapOf<Double, Char>()
//    var x = 0
//    while (x != numberList.size) {
//        val giveCoordinate = mutableMapOf<Double, Char>()
//        val reverseGiveCoordinate = mutableMapOf<Double, Char>()
//        var y = -1
//        //here is the problem
//        while (y != numberList.size - 1) {
//            y += 1
//            giveCoordinate["1${y}1.1${x}1".toDouble()] = numberList[y][x]
//            reverseGiveCoordinate["1${x}1.1${y}1".toDouble()] = numberList[y][x]
//            normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<Double, Char>
//            reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<Double, Char>
//        }
//        x += 1
//    }
//    println("normalCoordinate--------= $normalCoordinate")
//    println("reverseCoordinate-------= $reverseCoordinate")
//
////Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//    val filterCoordinate = normalCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()
//
//    val filterReverseCoordinate = reverseCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()
//    println("filterCoordinate--------= $filterCoordinate")
//    println("filterReverseCoordinate-= $filterReverseCoordinate")
//
////Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//    var pair = mutableListOf<Pair<Char?, Char?>>()
//    for ((i, _) in reverseCoordinate) {
//        val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i]))
//        pair = (pair + joinCoordinate) as MutableList<Pair<Char?, Char?>>
//    }
//    println("pair--------------------= $pair")
//
////отфильтровывает null значения
//    val nullFilter = pair.filter { (i, e) -> i != null && e != null }
//    println("nullFilter--------------= $nullFilter")
//
////от каждой пары начисляются очки в зависимости от первого номера
//    val point = mutableListOf<Int>()
//    for ((d, s) in nullFilter) {
//        when {
//            (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//            (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//            else -> point.add(1)
//        }
//    }
//    println("point-------------------= $point")
//
////добавляет значения вместе в зависимости от размера списка
//    var score = mutableListOf<Int>()
//    var combineDelete = mutableListOf<Int>()
//    var g = 0
//    var f = 0
//    while(g != numberList.size) {
//        g += 1
//        while (f != (numberList.size -1) * g) {
//            combineDelete = (combineDelete + point[f]) as MutableList
//            f += 1
//        }
//        score = (score + combineDelete.sum()) as MutableList<Int>
//        println("combineDelete-----------= $combineDelete")
//        combineDelete.clear()
//    }
//    println("score-------------------= $score")
//
////объединяет имена и добавленные баллы вместе
//    val teamScore = mutableMapOf<String, Int>()
//    var r = -1
//    while (r != numberList.size - 1) {
//        r += 1
//        teamScore[nameList[r]] = score[r]
//
//    }
//    println("teamScore---------------= $teamScore")
//    return teamScore
//}

//fun asf(Text: File):MutableMap<String,Int>{
//
////из текстового файла в список. Считывает каждую строку и преобразует каждую строку в индекс списка
//    val fileList = Text.readLines()
//
////вынимает все, кроме алфавитов без каких-либо пробелов и все, кроме цифр без каких-либо пробелов
//    var numberList = mutableListOf<String>()
//    var nameList = mutableListOf<String>()
//    for(i in fileList){
//    numberList = (numberList + i.replace(Regex("[^0-9]"), "")) as MutableList<String>
//    nameList = (nameList + i.replace(Regex("[ 0-9]"), "")) as MutableList<String>
//    }
//    println("numberList--------------= $numberList")
//    println("nameList----------------= $nameList")
//
////дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//    var normalCoordinate = mutableMapOf<Double, Char>()
//    var reverseCoordinate = mutableMapOf<Double, Char>()
//    var x = 0
//    while (x != numberList.size) {
//        val giveCoordinate = mutableMapOf<Double, Char>()
//        val reverseGiveCoordinate = mutableMapOf<Double, Char>()
//        var y = -1
//        //here is the problem
//        while (y != numberList.size - 1) {
//            y += 1
//            giveCoordinate["1${y}1.1${x}1".toDouble()] = numberList[y][x]
//            reverseGiveCoordinate["1${x}1.1${y}1".toDouble()] = numberList[y][x]
//            normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<Double, Char>
//            reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<Double, Char>
//        }
//        x += 1
//    }
//    println("normalCoordinate--------= $normalCoordinate")
//    println("reverseCoordinate-------= $reverseCoordinate")
//
////Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//    val filterCoordinate = normalCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()
//
//    val filterReverseCoordinate = reverseCoordinate.filter {
//            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()
//    println("filterCoordinate--------= $filterCoordinate")
//    println("filterReverseCoordinate-= $filterReverseCoordinate")
//
////Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//    var pair = mutableListOf<Pair<Char?, Char?>>()
//    for ((i, _) in reverseCoordinate) {
//        val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i]))
//        pair = (pair + joinCoordinate) as MutableList<Pair<Char?, Char?>>
//    }
//    println("pair--------------------= $pair")
//
////отфильтровывает null значения
//    val nullFilter = pair.filter { (i, e) -> i != null && e != null }
//    println("nullFilter--------------= $nullFilter")
//
////от каждой пары начисляются очки в зависимости от первого номера
//    val point = mutableListOf<Int>()
//    for ((d, s) in nullFilter) {
//        when {
//            (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//            (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//            else -> point.add(1)
//        }
//    }
//    println("point-------------------= $point")
//
////добавляет значения вместе в зависимости от размера списка
//    var score = mutableListOf<Int>()
//    var combineDelete = mutableListOf<Int>()
//    var g = 0
//    var f = 0
//    while(g != numberList.size) {
//        g += 1
//        while (f != (numberList.size -1) * g) {
//            combineDelete = (combineDelete + point[f]) as MutableList
//            f += 1
//        }
//        score = (score + combineDelete.sum()) as MutableList<Int>
//        println("combineDelete-----------= $combineDelete")
//        combineDelete.clear()
//    }
//    println("score-------------------= $score")
//
////объединяет имена и добавленные баллы вместе
//    val teamScore = mutableMapOf<String, Int>()
//    var r = -1
//    while (r != numberList.size - 1) {
//        r += 1
//        teamScore[nameList[r]] = score[r]
//
//    }
//    println("teamScore---------------= $teamScore")
//    return teamScore
//}
//Арсенал    0  4  1  1  0  4  1  1  0  4  1
//Бавария    1  0  2  3  1  0  2  3  1  0  2
//Интер      1  2  0  0  1  2  0  0  1  2  0
//Барселона  3  8  0  0  3  8  0  0  3  8  0
//lol        0  4  1  1  0  4  1  1  0  4  1
//lollord    1  0  2  3  1  0  2  3  1  0  2
//lolking    1  2  0  0  1  2  0  0  1  2  0
//lolgod     3  8  0  0  3  8  0  0  3  8  0
//kek        0  4  1  1  0  4  1  1  0  4  1
//keklord    1  0  2  3  1  0  2  3  1  0  2
//kekking    1  2  0  0  1  2  0  0  1  2  0
//
//101.101=0, 111.101=1, 121.101=1, 131.101=3, 141.101=0, 151.101=1, 161.101=1, 171.101=3, 181.101=0, 191.101=1, 1101.101=1,
//101.111=4, 111.111=0, 121.111=2, 131.111=8, 141.111=4, 151.111=0, 161.111=2, 171.111=8, 181.111=4, 191.111=0, 1101.111=2,
//101.121=1, 111.121=2, 121.121=0, 131.121=0, 141.121=1, 151.121=2, 161.121=0, 171.121=0, 181.121=1, 191.121=2, 1101.121=0,
//101.131=1, 111.131=3, 121.131=0, 131.131=0, 141.131=1, 151.131=3, 161.131=0, 171.131=0, 181.131=1, 191.131=3, 1101.131=0,
//101.141=0, 111.141=1, 121.141=1, 131.141=3, 141.141=0, 151.141=1, 161.141=1, 171.141=3, 181.141=0, 191.141=1, 1101.141=1,
//101.151=4, 111.151=0, 121.151=2, 131.151=8, 141.151=4, 151.151=0, 161.151=2, 171.151=8, 181.151=4, 191.151=0, 1101.151=2,
//101.161=1, 111.161=2, 121.161=0, 131.161=0, 141.161=1, 151.161=2, 161.161=0, 171.161=0, 181.161=1, 191.161=2, 1101.161=0,
//101.171=1, 111.171=3, 121.171=0, 131.171=0, 141.171=1, 151.171=3, 161.171=0, 171.171=0, 181.171=1, 191.171=3, 1101.171=0,
//101.181=0, 111.181=1, 121.181=1, 131.181=3, 141.181=0, 151.181=1, 161.181=1, 171.181=3, 181.181=0, 191.181=1, 1101.181=1,
//101.191=4, 111.191=0, 121.191=2, 131.191=8, 141.191=4, 151.191=0, 161.191=2, 171.191=8, 181.191=4, 191.191=0, 1101.191=2,
//101.1101=1, 111.1101=2, 121.1101=0, 131.1101=0, 141.1101=1, 151.1101=2, 161.1101=0, 171.1101=0, 181.1101=1, 191.1101=2, 1101.1101=0}

// (0) (4) (1) (1) | (0.0) (0.1) (0.2) (0.3)
// (1) (0) (2) (3) | (1.0) (1.1) (1.2) (1.3)
// (1) (2) (0) (0) | (2.0) (2.1) (2.2) (2.3)
// (3) (8) (0) (0) | (3.0) (3.1) (3.2) (3.3)

//fun main1() {
//    var x = 0
//    var f = 0
//    var g = 0
//    var r = -1
//
//    var name = mutableListOf<String>("Арсенал", "Бавария", "Интер", "Барселона")
//    var list = mutableListOf<List<Int>>(
//        listOf(0, 4, 1, 1),
//        listOf(1, 0, 2, 3),
//        listOf(1, 2, 0, 0),
//        listOf(3, 8, 0, 0),
//    )

////дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//    var norcor = mapOf<Double, Int>()
//    var revnorcor = mapOf<Double, Int>()
//    while (x != list.size) {
//        var givcor = mutableMapOf<Double, Int>()
//        var revgivcor = mutableMapOf<Double, Int>()
//        var y = -1
//        while (y != list.size - 1) {
//            y = y + 1
//            givcor["$y.$x".toDouble()] = list[y][x]
//            revgivcor["$x.$y".toDouble()] = list[y][x]
//            norcor = norcor + givcor
//            revnorcor = revnorcor + revgivcor
//        }
//        x = x + 1;
//    }
////Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//    val filcor = norcor.filter { (i, s) -> ((i * 10) % 11) != 0.0 }.toSortedMap()
//    val revfilcor = revnorcor.filter { (i, s) -> ((i * 10) % 11) != 0.0 }.toSortedMap()
////Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//    var pair = listOf<Pair<Int?, Int?>>()
//    for ((i, e) in revnorcor) {
//        var joincor = listOf(Pair(filcor[i], revfilcor[i]))
//        pair = pair + joincor
//    }
////отфильтровывает null значения
//    var nullfil = pair.filter { (i, e) -> i != null && e != null }
////от каждой пары начисляются очки в зависимости от первого номера
//    var point = mutableListOf<Int>()
//    for ((d, s) in nullfil) {
//        when {
//            (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//            (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//            else -> point.add(1)
//        }
//    }
////добавляет значения вместе в зависимости от размера списка
//    var unidel = mutableListOf<Int>()
//    var score = mutableListOf<Int>()
//    while(g != list.size) {
//        g = g + 1
//        while (f != (list.size -1) * g) {
//            unidel = (unidel + point[f]) as MutableList
//            f = f + 1
//        }
//        score = (score + unidel.sum()) as MutableList<Int>
//        unidel.clear()
//    }
////объединяет имена и добавленные баллы вместе
//    var tesco = mutableMapOf<String, Int>()
//    while (r != list.size - 1) {
//        r = r + 1
//        tesco[name[r]] = score[r]
//
//    }
//    println(tesco)
//}
//    var b = listOf<String>()
//    for(i in a){
//        b = b + i.split(Regex("[^0-9]"))
//    }
//    var c = listOf<String>()
//    for(i in b){
//        c = c + i.replace(",", "")
//
//    }