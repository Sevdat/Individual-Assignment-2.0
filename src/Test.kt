import java.io.File

fun main(){

//из текстового файла в список
    var fileList = mutableListOf<String>()

//считывает каждую строку и преобразует каждую строку в индекс списка
    File("src/text.txt").forEachLine {fileList = (fileList + listOf(it)) as MutableList<String>}

//вынимает все, кроме цифр без каких-либо пробелов
    var numberList = mutableListOf<String>()
    for(i in fileList){
        numberList = (numberList + i.replace(Regex("[^0-9]"), "" )) as MutableList<String>
    }

//вынимает все, кроме алфавитов без каких-либо пробелов
    var nameList = mutableListOf<String>()
    for(i in fileList){
        nameList = (nameList + i.replace(Regex("[ 0-9]"), "" )) as MutableList<String>
    }

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

//Фильтрует совпадающие координаты X и Y, и организует карту от наименьшего индекса до наибольшего индекса.
    val filterCoordinate = normalCoordinate.filter { (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) }.toSortedMap()
    val filterReverseCoordinate = reverseCoordinate.filter { (i, _) -> ((i % "${i.toInt()}.${i.toInt()}".toDouble()) != 0.0) }.toSortedMap()


   println(7.7% 7.7)
    println(filterCoordinate)
    println(filterReverseCoordinate)
}