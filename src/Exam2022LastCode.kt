import java.io.File

/**
Билет 5 - расписание аэропорта
*В файле с именем іnputName содержится расписание прибнтия самолетов и отправления самолетов
*аэропорта Москвы в следующем формате:
*LD210 Petersburg -> 13:45
* LV2222 Frankfurt -> 15:10
* FV1234 Petersburg -> 16:22
* AF345 Paris -> 17:20
* LD220 Petersburg -> 17:52
* BA457 London -> 18:30
* LV2223 Frankfurt <- 18:10
* FV1235 Petersburg <- 19:42
* JA4590 Tokyo <- 18:42
* AF345 Nice <- 22:20
*
* В каждой строчке файла имеется код рейса, название города вылета (без пробелов) и ->
* для прибывающих рейсов или название города назначения и <-
* для отправляющихся рейсов, время прибытия или отправления.
* Функция для названий городов src (город вылета) и
* dst (город прилёта) определяет возможность перелета с пересадкой в аэропорту Москвы.
*
"Удовлетворительно"
* --определить только возможность перелёта
* (да, если есть прибывающий рейс из src и отправляющийся в dst)
*
* "Хорошо"
* -- учесть также, что прибывающий рейс должен прилетать
* минимум на полчаса раньше отправления второго рейса.
* В результате функции указать коды рейсов прибытия и отправления
*
* "Отлично"
* вернуть список из всех подходящих пар рейсов прибытия и отправления,
* упорядоченный по возрастанию интервала между ними
*
* При нарушении форматов входных данных следует выбрасывать
* исключение IllegalArgumentException, При невозможности
прочитать файл выбрасывать исключение IOException.

Предложить имя и тип результата функции, Кроме функции
следует написать тесты, подтверждающие её работоспособность.
 */

fun lastCode(inputName: String, src: String, dst: String): Any{
    val data = File(inputName).readLines()
    val list1 = mutableListOf<String>()
    val list2 = mutableListOf<String>()
    var list3 = mutableListOf<String>()
    var x = 0
    for  (i in data){
        val split = i.split(" ")
        val land = split[1]
        val arrow = split[2]
        when {
            (src == land && arrow == "<-") -> list1 += i
            (dst == land && arrow == "->") -> list2 += i
        }
        x+=1
        if (x == data.size && list1 != emptyList<String>() && list2 != emptyList<String>()) {
            for (e in list1) for (k in list2){
                val time1 = e.split(" ")[3].split(":")
                val time2 = k.split(" ")[3].split(":")
                if (time1[0].toInt()*3600 + time1[1].toInt()*60
                    - time2[0].toInt()*3600 - time2[1].toInt()*60 <= 1800) list3 += "$e|--|$k"


            }
        }


    }
    if (list3 != emptyList<String>()) list3 = list3.distinct().toMutableList()
    if (list3 != emptyList<String>()) println(list3)
    return if (list1.size > 0 && list2.size > 0) "yes" else "no"
}









