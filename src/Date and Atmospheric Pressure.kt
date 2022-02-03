import java.io.File

fun foo(inputName: String, query: String, outputName: String) {
    val data = File(inputName).readLines().joinToString().replace(",,",",").split(",")
    var n = 0
    val pairIntString = mutableListOf<List<Pair<String,String>>>()
    while (n != data.size - 1){
        val k = data[n].split(":")
        pairIntString += mutableListOf(Pair(k[0].trim(), k[1].trim()))
        n += 1
    }

    n = 0
    val stringSplit = query.split(" ")
    val sign = stringSplit[1]
    val endValue = stringSplit.last()
    val startValue = stringSplit.first()
    var datePressure = ""
    var j = ""
    while (n != pairIntString.size) {

        for ((i,e) in pairIntString[n]) {

            if (stringSplit.size == 5) {
                when (stringSplit[1] + stringSplit[3]) {
                    ">>" -> if (e >= startValue && e >= endValue) datePressure += "$i: $e, "
                    "<<" -> if (e <= startValue && e <= endValue) datePressure += "$i: $e, "
                    "<>" -> if (e <= startValue && e >= endValue) datePressure += "$i: $e, "
                    "><" -> if (e >= startValue && e <= endValue) datePressure += "$i: $e, "
                }
            } else if (endValue != "prev") {
                when (sign) {
                    ">" -> if (e >= endValue) datePressure += "$i: $e, "
                    "<" -> if (e <= endValue) datePressure += "$i: $e, "
                }
            } else if (endValue == "prev"){
                when (sign) {
                    ">" -> if (e > j) datePressure += "$i: $e, "
                    "<" -> if (e < j) datePressure += "$i: $e, "
                }
                j = e
            }

        }

        n += 1
    }

    File(outputName).printWriter().use { e -> e.println(datePressure.dropLast(2)) }
    println(datePressure.dropLast(2))
}

//fun foo(inputName: String, query: String, outputName: String) {
//    var y = File(inputName).readLines().joinToString()
//    var n = 0
//    val splitComma = y.split(",")
//    val listString = mutableListOf<String>()
//    val pairIntString = mutableListOf<List<Pair<String,Int>>>()
//    while (n != splitComma.size - 1){
//        listString += splitComma[n].split(":")
//        pairIntString +=
//            mutableListOf(Pair(listString[0].filter { e -> e != ' ' }, listString[1].filter { e -> e != ' ' }.toInt()))
//        listString.clear()
//        n += 1
//    }
//
//    n = 0
//    val stringSplit = query.split(" ")
//    val sign = stringSplit[1]
//    val prev = stringSplit.last()
//    var datePressure = mutableListOf<String>()
//    var j = 0
//    while (n != pairIntString.size) {
//
//        for ((i,e) in pairIntString[n]) {
//
//
//            if (stringSplit.size == 5) {
//                val endValue = stringSplit.last().toInt()
//                val startValue = stringSplit.first().toInt()
//                when (stringSplit[1]+ stringSplit[3]) {
//                    ">>" -> if (e >= startValue && e >= endValue) { datePressure += "$i: $e" }
//                    "<<" -> if (e <= startValue && e <= endValue) { datePressure += "$i: $e" }
//                    "<>" -> if (e >= startValue && e >= endValue) { datePressure += "$i: $e" }
//                    "><" -> if (e >= startValue && e <= endValue) { datePressure += "$i: $e" }
//                }
//            }
//
//            if (prev != "prev" && stringSplit.size == 3) {
//                val endValue = stringSplit.last().toInt()
//                when (sign) {
//                    ">" -> if (e >= endValue) { datePressure += "$i: $e" }
//                    "<" -> if (e <= endValue) { datePressure += "$i: $e" }
//                }
//            }
//
//            if (prev == "prev"){
//                when (sign) {
//                    ">" -> if (e > j) { datePressure += "$i: $e" }
//                    "<" -> if (e < j) { datePressure += "$i: $e" }
//                }
//                j = e
//            }
//
//        }
//
//
//        n += 1
//    }
//      val x = File(outputName).printWriter().use { e -> e.println(datePressure.joinToString()) }
//    println(datePressure.joinToString())
//}

//        for ((i,e) in pairIntString[n]){
//
//            if (stringSplit.size == 5) {
//            var org1 = query.split(" ")[1]
//            var org2 = query.split(" ")[3]
//
//                when (org1 == "<" && org2 == "<") {
//                    (e <= endValue) -> datePressure += "$i: $e"
//                }
//                when (org1 == "<" && org2 == "<") {
//                    (e >= endValue) -> datePressure += "$i: $e"
//                }
//            }
//
//
//            if (prev != "prev") {
//                when (sign == "<") {
//                    (e <= endValue) -> datePressure += "$i: $e"
//                }
//                when (sign == ">") {
//                    (e >= endValue) -> datePressure += "$i: $e"
//                }
//            }
//
//
//        }

//fun foo1(inputName: String, query: String, outputName: String) {
//    var n = 0
//    var list0 = inputName.split(",")
//    var list1 = mutableListOf("")
//    var list2 = mutableListOf<List<Pair<String,Int>>>()
//
//    while (n != list0.size){
//        list1 += list0[n].split(":")
//        list2 += mutableListOf(Pair(list1[1], list1[2].filter { e -> e != ' ' }.toInt()))
//        list1 = mutableListOf("")
//        n += 1
//    }
//    n = 0
//    val qr = query.split(' ')
//    var keko = 0
//    var keko1 = 0
//    while (n != list2.size) {
//        for ((i, e) in list2[n]) {
////"x < prev"
//            if (qr[2] == "prev") {
//                if (qr[1] == "<") {
//                    keko1 = e
//                    if (keko1 <= keko) {
//
//                        print("$i: $e, ")
//                        keko = e
//                        keko1 += keko
//
//                    }
//                }
//                if (qr[1] == ">") {
//                    keko1 = e
//                    if (keko1 >= keko) {
//
//                        print("$i: $e, ")
//                        keko = e
//                        keko1 += keko
//
//                    }
//                }
//            }
////"x < 745"
//            if (qr.size == 3 && qr[2] != "prev") {
//                if (qr[1] == "<") {
//                    if (e <= qr[2].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//                if (qr[1] == ">") {
//                    if (e >= qr[2].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//            }
////"750 > x < 740"
//            if (qr.size == 5) {
//                if (qr[1] == "<" && qr[3] == "<") {
//                    if (e <= qr[0].toInt() && e <= qr[4].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//                if (qr[1] == ">" && qr[3] == ">") {
//                    if (e >= qr[0].toInt() && e >= qr[4].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//                if (qr[1] == "<" && qr[3] == ">") {
//                    if (e <= qr[0].toInt() && e >= qr[4].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//                if (qr[1] == ">" && qr[3] == "<") {
//                    if (e >= qr[0].toInt() && e <= qr[4].toInt()) {
//                        print("$i: $e, ")
//                    }
//                }
//            }
//
//        }
//        n += 1
//    }
//
//}



//  01.01: 755,
//  05.01: 758,
//  12.01: 765,
//  20.01: 768,                   (1)
//  (24.01: 763),    |  "x < prev" - 24.01: 763, |
//  30.01: 756,      |               30.01: 756, |
//  2.02: 752,       |               2.02: 752,  |            (2)                              (3)
//  5.02: 745,       |               5.02: 745,  | | "x < 745" - 5.02: 745, | | "750 > x < 740" - 5.02: 745, |
//  10.02: 739,      |               10.02: 739, | |            10.02: 739, | |  {739 < 740}   !(10.02: 739) |
//  14.02: 742       | {739 < 742} !(14.02: 742) | |            14.02: 742, | |                  14.02: 742, |


//fun main(){
//    var list0 = listOf(
//        "01.01: 755",
//        "05.01: 758",
//        "12.01: 765",
//        "20.01: 768",
//        "24.01: 763",
//        "30.01: 756",
//        "2.02: 752",
//        "5.02: 745",
//        "10.02: 739",
//        "14.02: 742"
//    )
//
//    var n = 0
//    var list1 = mutableListOf("")
//    var list2 = mutableListOf<List<Pair<String,Int>>>()
//
//    while (n != list0.size){
//        list1 += list0[n].split(":")
//        list2 += mutableListOf(Pair(list1[1], list1[2].filter { e -> e != ' ' }.toInt()))
//        list1 = mutableListOf("")
//        n += 1
//    }
//    println(list2)
//    n = 0
//    var x = 745
//    var list3 = mutableListOf<List<Pair<String,String>>>()
//    while (n != list2.size) {
//        for ((i, e) in list2[n]) {
//            if (e <= x) {
//                println("$i: $e")
//            }
//        }
//        n += 1
//    }
//}

/**
Севдат, отправляю вам задание на повторный зачёт.
Даю вам неделю на решение, 7 февраля буду проверять.
Всё как обычно — выложить в репозиторий, сделать тесты.
Со всеми вопросами — ко мне.
Пользоваться чьей-либо помощью вам не разрешается, (what if I pray to god?)
если узнаю — сразу поставлю незачёт. (too late)
**/
/*
* В файле с именем, заданным параметром inputName содержится величина атмосферного давления
* в различные дни года, например:
*
* 01.01: 755, 05.01: 758, 12.01: 765, 20.01: 768, 24.01: 763, 30.01: 756,
* 2.02: 752, 5.02: 745, 10.02: 739, 14.02: 742
*
* Даты приводятся в формате День.Месяц, после даты следует двоеточие,
* величина атмосферного давления в мм, и через запятую -- следующая дата.
* Между запятой и следующей датой могут присутствовать переводы строк
* в любом количестве. Даты следуют в календарном порядке, по возрастанию.
*
* В параметре query передаётся строковый запрос, по которому следует найти
* соответствующие величины давления и вернуть их как результат функции
* в формате, аналогичном входному (см. примеры ниже).
*
* "x < prev" — давление в данный день меньше, чем давление в предыдущий день в списке.
* Пример ответа на "x < prev": 24.01: 763, 30.01: 756, 2.02: 752, 5.02: 745, 10.02: 739.
* "x > prev" — давление в данный день больше, чем давление в предыдущий день в списке.
* "x < 745" — найти все дни, когда давление меньше 745
* Пример ответа: 10.02: 739, 14.02: 742.
* "x < 750 and x > 740" — найти все дни, когда давление в заданном интервале (740..750)
* Пример ответа: 5.02: 745, 14.02: 742.
*
* При нарушении формата входных данных следует выбрасывать
* исключение IllegalArgumentException.
*
* Самостоятельно предложить имя функции.
*/

//fun foo(inputName: String, query: String, outputName: String) { TODO() } // ?????????