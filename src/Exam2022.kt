import java.io.File

/**
 * Билет 2 - максимальное количество осадков

в файле с именем іnputName заданы ежедневные сведения
о количестве выпавииех осадков (в мм)
в различные месяцы года, всего не более
чем 31 значение в каждой строке и не более 12
строк во всём файле , например:

Март 0 1 0 3 41 2 0 0 13 16 20 8 0 4 8 1 0 0 0 7 12 0 4 9
Апрель 0 0 0 17 0 0 11 48 42 0 0 1 7 15 18 0 0 0 0 0 8 2 17 0
Май 10 15 48 21 0 0 17 22 30 0 0 13 0 0 2 5 7 0 0 0 1 10 3

Каждая строка начинается с названия месяца, за которым следует
последовательность целых чисел - уровень осадков
в мм в различные дни этого месяца, начиная с 1-го.
Порядок месяцев в файле должен соответствовать реальному
(следующий месяц всегда ниже предыдущего).

В строковом параметре days задан интервал дат
либо в формате "Апрель 9…15" (дни в одном месяце),
либо в формате "Март 22. Май 8" (дни в разных месяцах).
Необходимо рассчитать максимальный уровень осадков за одиндень в заданном интервале дат.
Например, для "Апрель 9..15" это 42, для "Март 22..Май 8" это 48.
Отсутствующие дни игнорировать.

"Удовлетворительно"
используется только первый формат для
параметра days - все дни в одном месяце

"Хорошо" - может использоваться как первый,
так и второй формат для параметра days,
то есть, интервал может содержать дни в разных месяцах

"Отлично" - результат функции должен содержать не только
максимальный уровень осадков, но и список дней, в которых он был достигнут
(42, 9 апреля или 48, 8 апреля, 3 мая для примеров выше)

При нарушении форматов входных данных следует выбрасывать
исключение IllegalArgumentException. При невозможности
прочитать файл выбрасывать исключение IOException.

Предложить имя и тип результата функции. Кроме функции
следует написать тесты, подтверждающие её работоспособность.

fun myFun(inputName: String, days: String): Any = TODO()
 */


fun myFun(inputName: String, days: String): Any {

    var interval =
        days.replace(Regex("""[^0-9]""")," ")
            .trim().split(" ").filter { e -> e != ""}
    var month =
        days.replace(Regex("""[0-9]""")," ").replace("...", "")
            .trim().split(" ").filter { e -> e != ""}.toMutableList()

    var monthMap = mutableMapOf(
        "Январь" to "0", "Февраль" to "0","Март" to "0",
        "Апрель" to "0", "Май" to "0", "Июнь" to "0",
        "Июль" to "0", "Август" to "0", "Сентябрь" to "0",
        "Октябрь" to "0", "Ноябрь" to "0", "Декабрь" to "0"
    )

    val data = File(inputName).readLines()
    var x = 0
    var d = ""
    var dv = ""
    while (x != data.size){
        d = data[x].split(" ").drop(1).joinToString().filter { e -> e != ',' }
        dv = data[x].split(" ")[0]
        monthMap[dv] = d
        x += 1
    }

    monthMap = monthMap.filterValues { s -> s.split(" ").size > 1} as MutableMap<String, String>

x =0

    var final = ""
    var monthdoor = 0
    var daydoor = 0
    var collector = 0
    var date = ""
    while (final != month[0]) {

            for ((i, e) in monthMap) {

                when(month.size){
                  2 -> if (month[0] == i) monthdoor = 1
                  1 -> if (month[0] == i) monthdoor = 1
                }

                if (monthdoor == 1) {
                    val f = e.split(" ")
                    for ((index,day) in f.map { it.toInt() }.withIndex()) {
                        if(interval[0].toInt() -1 == index && month[0] == i) daydoor = 1

                        if(daydoor == 1){
                        if (day > collector) {
                            collector = day
                            date = "$i ${index + 1} - $day"
                        }
                        }

                        when(month.size) {
                          2 ->  if (interval[1].toInt() == index && month[1] == i) daydoor = 0
                          1 ->  if (interval[1].toInt() == index) daydoor = 2
                        }

                    }
                }
                when(month.size){
                    2 -> if (month[1] == i) monthdoor = 0
                    1 -> if (daydoor == 2) monthdoor = 0
                }
                when (month.size){
                    2 -> if (month[1] == i) final = month[0]
                    1 -> if (daydoor == 2) final  = month[0]
                }

           }

        }
    println(date)
    return date
}

//    var y= ""
//    var g =""
//    var c ="0"
//    while (x != time.size) {
//        var kek = 0
//        var fd = 0
//       if (time[x] == month[0]) while (y != month[1]){
//
//            while (kek != growth[x].size){
//
//                g = growth[x][kek]
//                if (c.toInt() < growth[x][kek].toInt()) c = g
//                kek +=1
//
//            }
//
//           y=time[fd]
//            fd += 1
//        }
//
//        x += 1
//    }

//
//    for ((i,e) in monthMap){
//        if (i == month[0]) month[0] = e
//        when (month.size) {
//            2 -> if (i == month[1]) month[1] = e
//        }
//    }
//    month.sort()

//    var list = mutableListOf<List<String>>()
//    for((i,e) in map){
//        list += mutableListOf<List<String>>(e)
//    }
//    println(list)
//    x=0
//    var kek = "0"

//while(x != month[1].toInt()-month[0].toInt()){
//    var y = 0
//    while(y != list[month[0].toInt() + x].size ) {
//       if(list[month[0].toInt() + x][y] > kek) kek = list[month[0].toInt() + x][y]
//        y+=1
//    }
//
//    x += 1
//}

//    for ((i,e) in monthMap){
//    val y = i
//    val x = e.split(" ")
//
//        if (i == month[0])
//
//    }












