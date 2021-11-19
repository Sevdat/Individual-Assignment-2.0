import java.io.File

fun main(){

//из текстового файла в список. Считывает каждую строку и преобразует каждую строку в индекс списка
    var fileList = mutableListOf<String>()
    File("src/text.txt").forEachLine {fileList = (fileList + listOf(it)) as MutableList<String>}
    println("fileList----------------= $fileList")

//вынимает все, кроме алфавитов без каких-либо пробелов и все, кроме цифр без каких-либо пробелов
    var numberList = mutableListOf<String>()
    var nameList = mutableListOf<String>()
    for(i in fileList){
    numberList = (numberList + i.replace(Regex("[^0-9]"), "")) as MutableList<String>
    nameList = (nameList + i.replace(Regex("[ 0-9]"), "")) as MutableList<String>
    }
    println("numberList--------------= $numberList")
    println("nameList----------------= $nameList")
    
//дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
    var normalCoordinate = mutableMapOf<Double, Char>()
    var reverseCoordinate = mutableMapOf<Double, Char>()
    var x = 0
    while (x != numberList.size) {
        val giveCoordinate = mutableMapOf<Double, Char>()
        val reverseGiveCoordinate = mutableMapOf<Double, Char>()
        var y = -1
        while (y != numberList.size - 1) {
            y += 1
            giveCoordinate["$y.$x".toDouble()] = numberList[y][x]
            reverseGiveCoordinate["$x.$y".toDouble()] = numberList[y][x]
            normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<Double, Char>
            reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<Double, Char>
        }
        x += 1
    }
    println("normalCoordinate--------= $normalCoordinate")
    println("reverseCoordinate-------= $reverseCoordinate")

//Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
    val filterCoordinate = normalCoordinate.filter {
            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()

    val filterReverseCoordinate = reverseCoordinate.filter {
            (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()
    println("filterCoordinate--------= $filterCoordinate")
    println("filterReverseCoordinate-= $filterReverseCoordinate")

//Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
    var pair = mutableListOf<Pair<Char?, Char?>>()
    for ((i, _) in reverseCoordinate) {
        val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i]))
        pair = (pair + joinCoordinate) as MutableList<Pair<Char?, Char?>>
    }
    println("pair--------------------= $pair")

//отфильтровывает null значения
    val nullFilter = pair.filter { (i, e) -> i != null && e != null }
    println("nullFilter--------------= $nullFilter")
    
//от каждой пары начисляются очки в зависимости от первого номера
    val point = mutableListOf<Int>()
    for ((d, s) in nullFilter) {
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
}
// import java.io.File

// fun main(){

// //из текстового файла в список
//     var fileList = mutableListOf<String>()

// //считывает каждую строку и преобразует каждую строку в индекс списка
//     File("src/text.txt").forEachLine {fileList = (fileList + listOf(it)) as MutableList<String>}
//     println("fileList----------------= $fileList")

// //вынимает все, кроме цифр без каких-либо пробелов
//     var numberList = mutableListOf<String>()
//     for(i in fileList){
//     numberList = (numberList + i.replace(Regex("[^0-9]"), "" )) as MutableList<String>
//     }
//     println("numberList--------------= $numberList")

// //вынимает все, кроме алфавитов без каких-либо пробелов
//     var nameList = mutableListOf<String>()
//     for(i in fileList){
//         nameList = (nameList + i.replace(Regex("[ 0-9]"), "" )) as MutableList<String>
//     }
//     println("nameList----------------= $nameList")

// //дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//     var normalCoordinate = mutableMapOf<Double, Char>()
//     var reverseCoordinate = mutableMapOf<Double, Char>()
//     var x = 0
//     while (x != numberList.size) {
//         val giveCoordinate = mutableMapOf<Double, Char>()
//         val reverseGiveCoordinate = mutableMapOf<Double, Char>()
//         var y = -1
//         while (y != numberList.size - 1) {
//             y += 1
//             giveCoordinate["$y.$x".toDouble()] = numberList[y][x]
//             reverseGiveCoordinate["$x.$y".toDouble()] = numberList[y][x]
//             normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<Double, Char>
//             reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<Double, Char>
//         }
//         x += 1
//     }
//     println("normalCoordinate--------= $normalCoordinate")
//     println("reverseCoordinate-------= $reverseCoordinate")


// //Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//     val filterCoordinate = normalCoordinate.filter {
//             (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()

//     val filterReverseCoordinate = reverseCoordinate.filter {
//             (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()
//     println("filterCoordinate--------= $filterCoordinate")
//     println("filterReverseCoordinate-= $filterReverseCoordinate")

// //Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//     var pair = mutableListOf<Pair<Char?, Char?>>()
//     for ((i, _) in reverseCoordinate) {
//         val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i]))
//         pair = (pair + joinCoordinate) as MutableList<Pair<Char?, Char?>>
//     }
//     println("pair--------------------= $pair")

// //отфильтровывает null значения
//     val nullFilter = pair.filter { (i, e) -> i != null && e != null }
//     println("nullFilter--------------= $nullFilter")


// //от каждой пары начисляются очки в зависимости от первого номера
//     val point = mutableListOf<Int>()
//     for ((d, s) in nullFilter) {
//         when {
//             (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//             (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//             else -> point.add(1)
//         }
//     }
//     println("point-------------------= $point")

// //добавляет значения вместе в зависимости от размера списка
//     var score = mutableListOf<Int>()
//     var combineDelete = mutableListOf<Int>()
//     var g = 0
//     var f = 0
//     while(g != numberList.size) {
//         g += 1
//         while (f != (numberList.size -1) * g) {
//             combineDelete = (combineDelete + point[f]) as MutableList
//             f += 1
//         }
//         score = (score + combineDelete.sum()) as MutableList<Int>
//         println("combineDelete-----------= $combineDelete")
//         combineDelete.clear()
//     }
//     println("score-------------------= $score")

// //объединяет имена и добавленные баллы вместе
//     val teamScore = mutableMapOf<String, Int>()
//     var r = -1
//     while (r != numberList.size - 1) {
//         r += 1
//         teamScore[nameList[r]] = score[r]

//     }
//     println("teamScore---------------= $teamScore")

// }

// import java.io.File

// fun main(){

// //из текстового файла в список
//     var fileList = mutableListOf<String>()

// //считывает каждую строку и преобразует каждую строку в индекс списка
//     File("src/text.txt").forEachLine {fileList = (fileList + listOf(it)) as MutableList<String>}

// //вынимает все, кроме цифр без каких-либо пробелов
//     var numberList = mutableListOf<String>()
//     for(i in fileList){
//     numberList = (numberList + i.replace(Regex("[^0-9]"), "" )) as MutableList<String>
//     }

// //вынимает все, кроме алфавитов без каких-либо пробелов
//     var nameList = mutableListOf<String>()
//     for(i in fileList){
//         nameList = (nameList + i.replace(Regex("[ 0-9]"), "" )) as MutableList<String>
//     }

// //дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//     var normalCoordinate = mutableMapOf<Double, Char>()
//     var reverseCoordinate = mutableMapOf<Double, Char>()
//     var x = 0
//     while (x != numberList.size) {
//         val giveCoordinate = mutableMapOf<Double, Char>()
//         val reverseGiveCoordinate = mutableMapOf<Double, Char>()
//         var y = -1
//         while (y != numberList.size - 1) {
//             y += 1
//             giveCoordinate["$y.$x".toDouble()] = numberList[y][x]
//             reverseGiveCoordinate["$x.$y".toDouble()] = numberList[y][x]
//             normalCoordinate = (normalCoordinate + giveCoordinate) as MutableMap<Double, Char>
//             reverseCoordinate = (reverseCoordinate + reverseGiveCoordinate) as MutableMap<Double, Char>
//         }
//         x += 1
//     }

// //Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//     val filterCoordinate = normalCoordinate.filter {
//             (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0 && i !=0.0) }.toSortedMap()

//     val filterReverseCoordinate = reverseCoordinate.filter {
//             (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) && i !=0.0 }.toSortedMap()

// //Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//     var pair = mutableListOf<Pair<Char?, Char?>>()
//     for ((i, _) in reverseCoordinate) {
//         val joinCoordinate = listOf(Pair(filterCoordinate[i], filterReverseCoordinate[i]))
//         pair = (pair + joinCoordinate) as MutableList<Pair<Char?, Char?>>
//     }

// //отфильтровывает null значения
//     val nullFilter = pair.filter { (i, e) -> i != null && e != null }

// //от каждой пары начисляются очки в зависимости от первого номера
//     val point = mutableListOf<Int>()
//     for ((d, s) in nullFilter) {
//         when {
//             (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//             (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//             else -> point.add(1)
//         }
//     }

// //добавляет значения вместе в зависимости от размера списка
//     var score = mutableListOf<Int>()
//     var combineDelete = mutableListOf<Int>()
//     var g = 0
//     var f = 0
//     while(g != numberList.size) {
//         g += 1
//         while (f != (numberList.size -1) * g) {
//             combineDelete = (combineDelete + point[f]) as MutableList
//             f += 1
//         }
//         score = (score + combineDelete.sum()) as MutableList<Int>
//         combineDelete.clear()
//     }

// //объединяет имена и добавленные баллы вместе
//     val teamScore = mutableMapOf<String, Int>()
//     var r = -1
//     while (r != numberList.size - 1) {
//         r += 1
//         teamScore[nameList[r]] = score[r]

//     }
//     println(teamScore)

// }
// import java.io.File

// fun main(){
// //переменные для циклов
//     var x = 0
//     var f = 0
//     var g = 0
//     var r = -1

// //из текстового файла в список
//     var fili = listOf<String>()

// //считывает каждую строку и преобразует каждую строку в индекс списка
//     var loca = File("src/text.txt").forEachLine {fili = fili + listOf<String>(it)}

// //вынимает все, кроме цифр без каких-либо пробелов
//     var numli = listOf<String>()
//     for(i in fili){
//     numli = numli + i.replace(Regex("[^0-9]"), "" )
//     }

// //вынимает все, кроме алфавитов без каких-либо пробелов
//     var namli = listOf<String>()
//     for(i in fili){
//         namli = namli + i.replace(Regex("[ ^0-9]"), "" )
//     }

// //дает координаты X и Y в формате двойного числа в первом списке, а во втором списке он выдает обратную координату
//     var norcor = mapOf<Double, Char>()
//     var revnorcor = mapOf<Double, Char>()
//     while (x != numli.size) {
//         var givcor = mutableMapOf<Double, Char>()
//         var revgivcor = mutableMapOf<Double, Char>()
//         var y = -1
//         while (y != numli.size - 1) {
//             y = y + 1
//             givcor["$y.$x".toDouble()] = numli[y][x]
//             revgivcor["$x.$y".toDouble()] = numli[y][x]
//             norcor = norcor + givcor
//             revnorcor = revnorcor + revgivcor
//         }
//         x = x + 1;
//     }

// //Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
//     val filcor = norcor.filter { (i, s) -> ((i * 10) % 11) != 0.0 }.toSortedMap()
//     val revfilcor = revnorcor.filter { (i, s) -> ((i * 10) % 11) != 0.0 }.toSortedMap()

// //Соответствует каждому индексу из первого списка и второго списка, потому что числа принадлежат друг другу
//     var pair = listOf<Pair<Char?, Char?>>()
//     for ((i, e) in revnorcor) {
//         var joincor = listOf(Pair(filcor[i], revfilcor[i]))
//         pair = pair + joincor
//     }

// //отфильтровывает null значения
//     var nullfil = pair.filter { (i, e) -> i != null && e != null }

// //от каждой пары начисляются очки в зависимости от первого номера
//     var point = mutableListOf<Int>()
//     for ((d, s) in nullfil) {
//         when {
//             (d.toString().toInt() > s.toString().toInt()) -> point.add(3)
//             (d.toString().toInt() < s.toString().toInt()) -> point.add(0)
//             else -> point.add(1)
//         }
//     }

// //добавляет значения вместе в зависимости от размера списка
//     var score = mutableListOf<Int>()
//     var unidel = mutableListOf<Int>()
//     while(g != numli.size) {
//         g = g + 1
//         while (f != (numli.size -1) * g) {
//             unidel = (unidel + point[f]) as MutableList
//             f = f + 1
//         }
//         score = (score + unidel.sum()) as MutableList<Int>
//         unidel.clear()
//     }

// //объединяет имена и добавленные баллы вместе
//     var tesco = mutableMapOf<String, Int>()
//     while (r != numli.size - 1) {
//         r = r + 1
//         tesco[namli[r]] = score[r]

//     }
//     println(tesco)

// }
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
