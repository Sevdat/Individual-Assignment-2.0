import java.io.File

fun main() {

//из текстового файла в список. Считывает каждую строку и преобразует каждую строку в индекс списка
    var fileList = mutableListOf<String>()
    File("src/text.txt").forEachLine {fileList = (fileList + listOf(it)) as MutableList<String>}
    println("fileList----------------= $fileList")

//вынимает все, кроме алфавитов без каких-либо пробелов и все, кроме цифр без каких-либо пробелов
    var numberList = mutableListOf<String>()
    var nameList = mutableListOf<String>()
    for (i in fileList) {
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
        //here is the problem
        while (y != numberList.size - 1) {
            y += 1
            giveCoordinate["1${y}1.1${x}1".toDouble()] = numberList[y][x]
            reverseGiveCoordinate["1${x}1.1${y}1".toDouble()] = numberList[y][x]
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