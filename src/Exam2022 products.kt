import java.io.File

/**
Билет 10 -- продукты на складе

В файле с именем іnputName заданы описания продуктов,
имекщихся на складе, в следующем формате:

001532 - яблоки, 49 р, 54 кг
004021 - молоко, 74 р, 72 уп
008243 - клеб, 59 р, 30 шт
002762 - свинина, 299 р, 0 кг
009724 - яйца, 69 р, 20 уп

Строчка начинается с шестизначного числового кода продукта,
после черточки через запятую перечисляются название продукта,
цена одной единицы продукта,  количество единиц продукта на складе

Параметр query содержит запрос, начинающийся с кода продукта,
с указанием через пробел минимально необходимого количества продукта.
Функция должна найти продукт с этим кодом в файле, вернув его название,
признак достаточности количества и общую стоимость таких продуктов на складе

"Удовлетворительно"-
- код продукта в запросе указывается явно,
например, "009724 15", ожидаемый ответ -- яйца, достаточно, общая стоимость 1380 р

"Хорошо"
в запросе код продукта может быть заменён
символом * (любой код), например, запросу "* 35" удовлетворяют все продукты,
но только яблоки и молоко присутствуют в достаточном количестве в коде продукта могут присутствовать

"Отлично"
знаки вопроса, обозначающие произвольную цифру на данном месте,
например, запросу "00???2 10" соответствуют яблоки и свинина,
причём количество свинины не является достаточным (< 10 кг)

При нарушении форматов входных данных следует выбрасывать
исключение IllegalArgumentException, при невозможности
прочитать файл выбрасывать исключение IOException.

Предложить имя и тип результата функции. Кроме функции следует написать тесты,
подтверядающие её работоспособность.

fun foo (inputName: String, query: String): Any = TODO()
 */


fun foo(inputName: String, query: String): Any {
 val demand = query.split(" ")
 val choice = demand[0].trim()
 val value = demand[1].trim()
 val corrupted = choice.split("").filter { e -> e != "" }

 val product = File(inputName).readLines()
 val second = mutableListOf<String>()
 for (i in product){
  val split = i.split("-")
  val number = split[0].trim()
  val matrix = number.split("").filter { e -> e != "" }

  val info = split.drop(1)[0].trim().split(",")
  val name = info[0].trim()
  val price = info[1].trim()
  val splice = price.split(" ")
  val amount = info[2].trim()
  val splitmount = amount.split(" ")

  var count = 0
  var question = 0
  var collect = ""
  var order = ""
  var bar = ""
   if (choice != "*") while(count != matrix.size){

    if(corrupted[count] == "?") question = 1
    when{
     corrupted[count] == "?" -> bar = matrix[count]
     matrix[count] != corrupted[count] -> bar = ""
     matrix[count] == corrupted[count] -> bar = matrix[count]
    }

    collect += bar
   if (collect.split("").filter { e -> e != ""  && e != " " }.size == corrupted.size) order += collect
    count +=1

    when (question){
     1 -> if (number == order && splitmount[0] >= value) second += name
     0 -> when {
      (number == order && splitmount[0] >= value)->
       second += "$name, достаточно, ${splitmount[0].toInt() * splice[0].toInt()} ${splice[1]}"
      (number == order && splitmount[0] < value) ->
       second += "$name, недостаточно, ${splitmount[0].toInt() * splice[0].toInt()} ${splice[1]}"
     }

    }

   } else if (splitmount[0] >= value) second += name

 }

 return second
}


















